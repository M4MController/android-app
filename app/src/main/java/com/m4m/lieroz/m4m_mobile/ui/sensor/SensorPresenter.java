package com.m4m.lieroz.m4m_mobile.ui.sensor;

import com.m4m.lieroz.m4m_mobile.data.DataManager;
import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;
import com.m4m.lieroz.m4m_mobile.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SensorPresenter<V extends SensorMvpView> extends BasePresenter<V> implements SensorMvpPresenter<V> {

    @Inject
    public SensorPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
