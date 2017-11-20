package in.nikita.busroute;

import java.util.List;

import in.nikita.busroute.model.RouteModel;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Nikita Gupta on 17-11-2017.
 */
public interface APIService {


    @GET("/v2/5808f00d10000005074c6340")
    Observable<List<RouteModel>> getRoutedata();

}