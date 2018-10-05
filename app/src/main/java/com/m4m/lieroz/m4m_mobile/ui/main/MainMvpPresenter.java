package com.m4m.lieroz.m4m_mobile.ui.main;

import com.m4m.lieroz.m4m_mobile.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void useDataManager();
}
