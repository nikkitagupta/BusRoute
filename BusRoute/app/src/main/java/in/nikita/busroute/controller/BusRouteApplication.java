package in.nikita.busroute.controller;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import in.nikita.busroute.utils.LruBitmapCache;

/**
 * Created by Nikita Gupta on 17-11-2017.
 */

public class BusRouteApplication extends Application {

    public static final String TAG = "RubiconApplication";
    private static BusRouteApplication appInstance;

    /**
     * Added RequestQueue related code for volley operations.
     */

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public BusRouteApplication() {
    }

    // Getter to access Singleton instance
    public static BusRouteApplication getInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Setup singleton instance
        appInstance = this;
        mRequestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(appInstance);
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
