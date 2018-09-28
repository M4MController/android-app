package com.m4m.lieroz.m4m_mobile.ui.object;

import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;

import javax.inject.Inject;

public class ObjectPresenter<V extends ObjectMvpView> extends BasePresenter<V> implements ObjectMvpPresenter<V> {

    @Inject
    public ObjectPresenter() {

    }
}
