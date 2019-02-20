package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.BuildConfig;

public abstract class ApiEndPoint {

    public static final String ENDPOINT_USER_AUTH = BuildConfig.BASE_AUTH_URL
            + "/sign_in";

    public static final String ENDPOINT_USER_RELATIONS = BuildConfig.BASE_API_URL
            + "/v2/user/relations";

    public static final String ENDPOINT_OBJECT_RELATIONS = BuildConfig.BASE_API_URL
            + "/v2/object/{id}/relations";

    public static final String ENDPOINT_SENSOR_DATA_PERIOD = BuildConfig.BASE_API_URL
            + "/v2/sensor/{id}/get_data_period";
}
