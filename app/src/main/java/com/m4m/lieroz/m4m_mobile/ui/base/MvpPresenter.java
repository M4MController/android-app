package com.m4m.lieroz.m4m_mobile.ui.base;

import com.androidnetworking.error.ANError;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(ANError error);
}
