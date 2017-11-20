package in.nikita.busroute.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.nikita.busroute.R;
import in.nikita.busroute.adapter.RecyclerAdapter;
import in.nikita.busroute.controller.BusRouteApplication;
import in.nikita.busroute.model.RouteModel;
import in.nikita.busroute.receiver.ConnectivityReceiver;
import in.nikita.busroute.serverconnectivity.NetworkOperation;
import in.nikita.busroute.utils.ActionFailedException;
import in.nikita.busroute.utils.ConstantUtils;
import in.nikita.busroute.utils.MethodUtils;

public class MainActivity extends BaseActivity implements Response.Listener, Response.ErrorListener{

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<RouteModel> routeModelList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar(toolbar, getResources().getString(R.string.route_name));
        addMenu(R.menu.toolbar_menu);
        context = this;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Checks the internet Connectivity if not prompts the message in Snackbar
        boolean isConnected = ConnectivityReceiver.isConnected();
        MethodUtils.showSnack(isConnected, MainActivity.this);
        if (isConnected) {
            getBusRouteData();
        }
    }

    // Fetch Bus routes data from API
    public void getBusRouteData(){
        MethodUtils.showProgressDialog(MainActivity.this, "Loading...");
        NetworkOperation strRequest = new NetworkOperation(Request.Method.GET, ConstantUtils.BUS_ROUTES_Url,
                this, this);
        BusRouteApplication.getInstance().addToRequestQueue(strRequest);
    }

    // Parse the JSON response fetched from the API
    private void parseRoutes(String jsonResponse) throws ActionFailedException {
        routeModelList = new ArrayList<>();
        try {
            JSONObject jsonObjectRoute = new JSONObject(jsonResponse);
            JSONArray jsonArray = jsonObjectRoute.getJSONArray("routes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString(ConstantUtils.TAG_ID);
                String name = jsonObject.getString(ConstantUtils.TAG_NAME);
                String description = jsonObject.getString(ConstantUtils.TAG_DESC);
                String accessible = jsonObject.getString(ConstantUtils.TAG_ACCESSIBLE);
                String imageUrl = jsonObject.getString(ConstantUtils.TAG_IMAGE);
                JSONArray stops = jsonObject.getJSONArray(ConstantUtils.TAG_STOPS);

                RouteModel routeModel = new RouteModel();
                routeModel.setId(id);
                routeModel.setName(name);
                routeModel.setDescription(description);
                routeModel.setAccessible(accessible);
                routeModel.setImage(imageUrl);
                routeModel.setStops(stops.toString());
                routeModelList.add(routeModel);
            }

        } catch (JSONException e) {
            new ActionFailedException(e.getLocalizedMessage());
        }
        setItemsInAdapter();
    }

    // Set the items in Adapter
    private void setItemsInAdapter(){
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context, routeModelList, new RecyclerAdapter.ButtonClickListener() {
            @Override
            public void onClicked(View view, int position) {
                RouteModel route = routeModelList.get(position);
                Toast.makeText(context, route.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DetailedActivity.class);
                intent.putExtra(ConstantUtils.PARCEL_DATA,route);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
        MethodUtils.cancelProgressData();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object result) {
        try {
            String jsonResponse = result.toString();
                parseRoutes(jsonResponse);
        } catch (ActionFailedException e) {
            MethodUtils.cancelProgressData();
            MethodUtils.showToast(context, e.getLocalizedMessage());
        }
    }
}
