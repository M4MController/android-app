package com.m4m.lieroz.m4m_mobile.ui.sensor;

import com.androidnetworking.error.ANError;
import com.m4m.lieroz.m4m_mobile.data.DataManager;
import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;
import com.m4m.lieroz.m4m_mobile.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SensorPresenter<V extends SensorMvpView> extends BasePresenter<V> implements SensorMvpPresenter<V> {

    @Inject
    public SensorPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getSensorData(int id, String from, String to, final boolean current) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getSensorDataApiCall(id, from, to)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SensorDataPeriodResponse>() {
                    @Override
                    public void accept(SensorDataPeriodResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        getMvpView().update(response.getData(), current);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void logOut() {
        getDataManager().updateUserInfo(DataManager.LoggedInMode.LOGGED_OUT);
    }
}
