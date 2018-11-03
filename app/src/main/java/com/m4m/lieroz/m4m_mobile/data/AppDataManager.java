package com.m4m.lieroz.m4m_mobile.data;

import android.content.Context;

import com.m4m.lieroz.m4m_mobile.data.network.ApiHelper;
import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.m4m.lieroz.m4m_mobile.data.prefs.PreferencesHelper;
import com.m4m.lieroz.m4m_mobile.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void setApiToken(String token) {
        mApiHelper.setApiToken(token);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        setApiToken(accessToken);
    }

    @Override
    public Single<String> doUserLoginApiCall(String email, String password) {
        return mApiHelper.doUserLoginApiCall(email, password);
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
