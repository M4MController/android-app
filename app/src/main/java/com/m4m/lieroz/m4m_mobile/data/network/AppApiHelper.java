package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private String mApiToken;

    @Inject
    public AppApiHelper() {

    }

    public void setApiToken(String token) {
        mApiToken = token;
    }

    @Override
    public Single<String> doUserLoginApiCall(String email, String password) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_USER_AUTH)
                .addStringBody("{\"e_mail\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .build()
                .getStringSingle();
    }

    @Override
    public Single<UserRelationsResponse> getUserRelationsApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_USER_RELATIONS)
                .addQueryParameter("token", mApiToken)
                .build()
                .getObjectSingle(UserRelationsResponse.class);
    }

    @Override
    public Single<SensorDataPeriodResponse> getSensorDataApiCall(int id, String from, String to) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SENSOR_DATA_PERIOD)
                .addQueryParameter("token", mApiToken)
                .addPathParameter("id", Integer.toString(id))
                .addQueryParameter("from", from)
                .addQueryParameter("to", to)
                .build()
                .getObjectSingle(SensorDataPeriodResponse.class);
    }
}
