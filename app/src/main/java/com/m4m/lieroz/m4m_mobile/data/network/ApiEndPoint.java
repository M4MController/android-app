package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.BuildConfig;

public abstract class ApiEndPoint {

    public static final String ENDPOINT_USER_RELATIONS = BuildConfig.BASE_URL
            + "/v2/user/relations";

    public static final String ENDPOINT_SENSOR_DATA_PERIOD = BuildConfig.BASE_URL
            + "/v2/sensor/{id}/get_data_period";
}
