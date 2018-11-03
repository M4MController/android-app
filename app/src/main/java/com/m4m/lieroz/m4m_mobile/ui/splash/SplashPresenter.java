package com.m4m.lieroz.m4m_mobile.ui.splash;

import com.m4m.lieroz.m4m_mobile.data.DataManager;
import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;
import com.m4m.lieroz.m4m_mobile.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        decideNextActivity();
    }

    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_OUT.getType()) {
            getMvpView().openAuthActivity();
        } else {
            getMvpView().openMainActivity();
        }
    }
}