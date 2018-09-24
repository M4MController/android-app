package com.m4m.lieroz.m4m_mobile.ui.main;

import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter() {

    }
}
