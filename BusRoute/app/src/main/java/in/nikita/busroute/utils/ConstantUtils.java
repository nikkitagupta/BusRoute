package in.nikita.busroute.utils;

/**
 * Created by Nikita Gupta on 17-11-2017.
 */

public class ConstantUtils {
    public static final String Base_Url = "http://www.mocky.io";
    public static final String BUS_ROUTES_Url = Base_Url + "/v2/5808f00d10000005074c6340";
    public static final int TIMEOUT_MS = 25000000;
    /**
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 0;

    /**
     * The default backoff multiplier
     */
    public static final float DEFAULT_BACKOFF_MULT = 2f;

    // Bus Route Constant
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESC = "description";
    public static final String TAG_ACCESSIBLE = "accessible";
    public static final String TAG_IMAGE = "image";
    public static final String TAG_STOPS = "stops";
    public static final String PARCEL_DATA = "parcel_data";
}
