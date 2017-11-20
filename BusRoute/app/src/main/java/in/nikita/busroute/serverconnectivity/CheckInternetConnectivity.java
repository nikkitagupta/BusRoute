package in.nikita.busroute.serverconnectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nikita on 17/11/17.
 */
public class CheckInternetConnectivity {
    private Context context;
    private static CheckInternetConnectivity instance;

    public CheckInternetConnectivity(Context context) {
        this.context = context;
    }

    public static CheckInternetConnectivity getInstance(Context context ) {
        if (instance == null) {
            instance = new CheckInternetConnectivity(context);
        }
        return instance;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if(netinfo !=null && netinfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }
    }
}
