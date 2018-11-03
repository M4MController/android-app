package com.m4m.lieroz.m4m_mobile.ui.auth;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.m4m.lieroz.m4m_mobile.data.DataManager;
import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;
import com.m4m.lieroz.m4m_mobile.utils.CommonUtils;
import com.m4m.lieroz.m4m_mobile.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class AuthPresenter<V extends AuthMvpView> extends BasePresenter<V> implements AuthMvpPresenter<V> {

    @Inject
    public AuthPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLoginClick(String email, String password) {
        if (email == null || email.isEmpty()) {
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return;
        }
        if (password == null || password.isEmpty()) {
            return;
        }

        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .doUserLoginApiCall(email, password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        getDataManager().updateUserInfo(DataManager.LoggedInMode.LOGGED_IN);
                        getDataManager().setAccessToken(response);

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        getMvpView().openMainActivity();
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
}
