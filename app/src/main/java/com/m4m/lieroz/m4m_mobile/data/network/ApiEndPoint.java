package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.BuildConfig;

public abstract class ApiEndPoint {

    public static final String ENDPOINT_USER_AUTH = BuildConfig.BASE_URL
            + ":4999/sign_in";

    public static final String ENDPOINT_USER_RELATIONS = BuildConfig.BASE_URL
            + ":5000/v2/user/relations";

    public static final String ENDPOINT_SENSOR_DATA_PERIOD = BuildConfig.BASE_URL
            + ":5000/v2/sensor/{id}/get_data_period";
}
