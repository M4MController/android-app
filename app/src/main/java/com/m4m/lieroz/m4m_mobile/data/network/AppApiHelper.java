package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.data.network.model.ObjectRelationsResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<String> doUserLoginApiCall(String email, String password) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_USER_AUTH)
                .addStringBody("{\"e_mail\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .build()
                .getStringSingle();
    }

    @Override
    public Single<UserRelationsResponse> getUserRelationsApiCall(String token) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_USER_RELATIONS)
                .addQueryParameter("token", token)
                .build()
                .getObjectSingle(UserRelationsResponse.class);
    }

    @Override
    public Single<ObjectRelationsResponse> getObjectRelationsApiCall(String token, int id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OBJECT_RELATIONS)
                .addPathParameter("id", Integer.toString(id))
                .addQueryParameter("token", token)
                .build()
                .getObjectSingle(ObjectRelationsResponse.class);
    }

    @Override
    public Single<SensorDataPeriodResponse> getSensorDataApiCall(String token, int id, String from, String to) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SENSOR_DATA_PERIOD)
                .addPathParameter("id", Integer.toString(id))
                .addQueryParameter("token", token)
                .addQueryParameter("from", from)
                .addQueryParameter("to", to)
                .build()
                .getObjectSingle(SensorDataPeriodResponse.class);
    }
}
