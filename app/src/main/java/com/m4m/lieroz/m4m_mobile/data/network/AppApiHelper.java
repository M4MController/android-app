package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<UserRelationsResponse> getUserRelationsApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_USER_RELATIONS)
                .build()
                .getObjectSingle(UserRelationsResponse.class);
    }

    @Override
    public Single<SensorDataPeriodResponse> getSensorDataApiCall(int id, String from, String to) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SENSOR_DATA_PERIOD)
                .addPathParameter("id", Integer.toString(id))
                .addQueryParameter("from", from)
                .addQueryParameter("to", to)
                .build()
                .getObjectSingle(SensorDataPeriodResponse.class);
    }
}
