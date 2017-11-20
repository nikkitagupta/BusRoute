package in.nikita.busroute.serverconnectivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import in.nikita.busroute.utils.ConstantUtils;

/**
 * Created by Nikita on 17/11/17.
 */

public class NetworkOperation extends StringRequest {
    private String mRequestBody;
    private Map<String,String> params;
    public NetworkOperation(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        setRetryPolicy(getMyOwnDefaultRetryPolicy());
    }
    public NetworkOperation(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String mRequestBody) {
        super(method, url, listener, errorListener);
        this.mRequestBody =mRequestBody;
        setRetryPolicy(getMyOwnDefaultRetryPolicy());
    }

    @Override
    public Map< String, String > getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        //headers.put(ConstantUtils.AUTHORIZATION, ConstantUtils.ACCESS_TOKEN_DATA);
        return headers;
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        return params;
    }
    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }
    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, "utf-8");
            return null;
        }
    }
    public DefaultRetryPolicy getMyOwnDefaultRetryPolicy() {
        return new DefaultRetryPolicy(
                ConstantUtils.TIMEOUT_MS,
                ConstantUtils.DEFAULT_MAX_RETRIES,
                ConstantUtils.DEFAULT_BACKOFF_MULT);
    }
}
