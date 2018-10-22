package com.m4m.lieroz.m4m_mobile.ui.object;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.m4m.lieroz.m4m_mobile.data.DataManager;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;
import com.m4m.lieroz.m4m_mobile.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ObjectPresenter<V extends ObjectMvpView> extends BasePresenter<V> implements ObjectMvpPresenter<V> {

    @Inject
    public ObjectPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void useDataManager() {
        getCompositeDisposable().add(getDataManager()
                .getUserRelationsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<UserRelationsResponse>() {
                    @Override
                    public void accept(UserRelationsResponse response) throws Exception {
                        getMvpView().update(response.getMessage().getSensors());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
