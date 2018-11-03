package com.m4m.lieroz.m4m_mobile.ui.auth;

import com.m4m.lieroz.m4m_mobile.ui.base.MvpPresenter;

public interface AuthMvpPresenter<V extends AuthMvpView> extends MvpPresenter<V> {

    void onLoginClick(String email, String password);
}
