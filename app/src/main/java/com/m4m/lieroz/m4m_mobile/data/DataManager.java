package com.m4m.lieroz.m4m_mobile.data;

import com.m4m.lieroz.m4m_mobile.data.network.model.ObjectRelationsResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;

import io.reactivex.Single;

public interface DataManager {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(LoggedInMode mode);

    String getAccessToken();

    void setAccessToken(String accessToken);

    Single<String> doUserLoginApiCall(String email, String password);

    Single<UserRelationsResponse> getUserRelationsApiCall();

    Single<ObjectRelationsResponse> getObjectRelationsApiCall(int id);

    Single<SensorDataPeriodResponse> getSensorDataApiCall(int id, String from, String to);

    void updateUserInfo(LoggedInMode mode);

    enum LoggedInMode {

        LOGGED_OUT(0),
        LOGGED_IN(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
