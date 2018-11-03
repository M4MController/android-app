package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;

import io.reactivex.Single;

public interface ApiHelper {

    Single<String> doUserLoginApiCall(String email, String password);

    Single<UserRelationsResponse> getUserRelationsApiCall(String token);

    Single<SensorDataPeriodResponse> getSensorDataApiCall(String token, int id, String from, String to);
}
