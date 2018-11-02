package com.m4m.lieroz.m4m_mobile.data;

import android.content.Context;
import android.util.Log;

import com.m4m.lieroz.m4m_mobile.data.network.ApiHelper;
import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.m4m.lieroz.m4m_mobile.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context, ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Single<UserRelationsResponse> getUserRelationsApiCall() {
        return mApiHelper.getUserRelationsApiCall();
    }

    @Override
    public Single<SensorDataPeriodResponse> getSensorDataApiCall(int id, String from, String to) {
        return mApiHelper.getSensorDataApiCall(id, from, to);
    }
}
