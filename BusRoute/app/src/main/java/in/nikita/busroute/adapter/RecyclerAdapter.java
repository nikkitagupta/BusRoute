package in.nikita.busroute.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.nikita.busroute.R;
import in.nikita.busroute.controller.BusRouteApplication;
import in.nikita.busroute.model.RouteModel;

/**
 * Created by Nikita Gupta on 17-11-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<RouteModel> routeModelList;
    private ImageLoader imageLoader = BusRouteApplication.getInstance().getImageLoader();
    private Context context;
    public ButtonClickListener buttonClickListener;

    public RecyclerAdapter(Context context, List<RouteModel> routeModelList, ButtonClickListener buttonClickListener ) {
        this.context = context;
        this.routeModelList = routeModelList;
        this.buttonClickListener = buttonClickListener;
    }

    public interface ButtonClickListener {
        void onClicked(View view, int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_item, parent, false);
        MyViewHolder myHolder = new MyViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (imageLoader == null)
            imageLoader = BusRouteApplication.getInstance().getImageLoader();
        RouteModel route = routeModelList.get(position);
        holder.routeName.setText(route.getName());
        String image = route.getImage();
        if (image != null)
            holder.imageView.setImageUrl(image, imageLoader);
    }

    @Override
    public int getItemCount() {
        return routeModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.busImage)
        NetworkImageView imageView;
        @BindView(R.id.iconImage)
        NetworkImageView iconImage;
        @BindView(R.id.routeName)
        TextView routeName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonClickListener.onClicked(view,getAdapterPosition());
                }
            });
        }
    }
}
