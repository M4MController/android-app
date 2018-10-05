package com.m4m.lieroz.m4m_mobile.data.network;

import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

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
}
