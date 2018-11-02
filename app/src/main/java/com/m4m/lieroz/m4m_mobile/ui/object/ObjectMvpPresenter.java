package com.m4m.lieroz.m4m_mobile.ui.object;

import com.m4m.lieroz.m4m_mobile.ui.base.MvpPresenter;

public interface ObjectMvpPresenter<V extends ObjectMvpView> extends MvpPresenter<V> {

    void getUserSensors();
}
