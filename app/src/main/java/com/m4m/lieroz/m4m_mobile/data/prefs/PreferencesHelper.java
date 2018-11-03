package com.m4m.lieroz.m4m_mobile.data.prefs;

import com.m4m.lieroz.m4m_mobile.data.DataManager;

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getAccessToken();

    void setAccessToken(String accessToken);
}
