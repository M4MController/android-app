package com.m4m.lieroz.m4m_mobile.ui.object;

import com.m4m.lieroz.m4m_mobile.di.PerActivity;
import com.m4m.lieroz.m4m_mobile.ui.base.MvpPresenter;

@PerActivity
public interface ObjectMvpPresenter<V extends ObjectMvpView> extends MvpPresenter<V> {

    void getUserSensors(int id);

    void logOut();
}
