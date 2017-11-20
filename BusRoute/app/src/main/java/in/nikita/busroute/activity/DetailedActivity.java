package in.nikita.busroute.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.nikita.busroute.R;
import in.nikita.busroute.controller.BusRouteApplication;
import in.nikita.busroute.model.RouteModel;
import in.nikita.busroute.utils.ActionFailedException;
import in.nikita.busroute.utils.ConstantUtils;

public class DetailedActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.routeDesc)
    TextView routeDesc;
    @Nullable
    @BindView(R.id.routeName)
    TextView routeName;
    @Nullable
    @BindView(R.id.iconImage)
    NetworkImageView iconImage;
    @Nullable
    @BindView(R.id.busImage)
    NetworkImageView busImage;
    @Nullable
    @BindView(R.id.stopsLL)
    LinearLayout stopsLL;

    private ImageLoader imageLoader = BusRouteApplication.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_row_item);
        ButterKnife.bind(this);
        RouteModel route = (RouteModel) getIntent().getParcelableExtra(ConstantUtils.PARCEL_DATA);
        try {
            parseRoute(route);
        } catch (Exception e) {
        }
    }

    private void parseRoute(RouteModel response) throws ActionFailedException {
        try {
            String title = response.getName();
            routeName.setText(title);
            String desc = response.getDescription();
            routeDesc.setVisibility(View.VISIBLE);
            routeDesc.setText(desc);
            String stops = response.getStops();


            JSONArray jsonArray = new JSONArray(stops);
            for(int i = 0 ; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String stopName = jsonObject.getString(ConstantUtils.TAG_NAME);
                TextView textView = new TextView(this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setText(stopName);
                textView.setTextSize(getResources().getDimension(R.dimen.text_medium));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
                stopsLL.addView(textView);
            }
            String image = response.getImage();
            boolean accessible = Boolean.parseBoolean(response.getAccessible());
            busImage.setImageUrl(image, imageLoader);
            if(accessible){
                iconImage.setVisibility(View.VISIBLE);
                iconImage.setImageUrl(image, imageLoader);
            }

        } catch (JSONException e) {
            new ActionFailedException(e.getLocalizedMessage());
        }

    }
}
