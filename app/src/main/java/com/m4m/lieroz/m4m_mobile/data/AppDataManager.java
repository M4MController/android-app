package com.m4m.lieroz.m4m_mobile.data;

import android.content.Context;

import com.m4m.lieroz.m4m_mobile.data.network.ApiHelper;
import com.m4m.lieroz.m4m_mobile.data.network.model.ObjectRelationsResponse;
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

    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    public Single<String> doUserLoginApiCall(String email, String password) {
        return mApiHelper.doUserLoginApiCall(email, password);
    }

    public Single<UserRelationsResponse> getUserRelationsApiCall() {
        return mApiHelper.getUserRelationsApiCall(mPreferencesHelper.getAccessToken());
    }

    public Single<ObjectRelationsResponse> getObjectRelationsApiCall(int id) {
        return mApiHelper.getObjectRelationsApiCall(mPreferencesHelper.getAccessToken(), id);
    }

    public Single<SensorDataPeriodResponse> getSensorDataApiCall(int id, String from, String to) {
        return mApiHelper.getSensorDataApiCall(mPreferencesHelper.getAccessToken() ,id, from, to);
    }

    @Override
    public void updateUserInfo(LoggedInMode mode) {
        setCurrentUserLoggedInMode(mode);
    }
}
