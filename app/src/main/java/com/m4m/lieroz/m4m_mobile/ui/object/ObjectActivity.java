package com.m4m.lieroz.m4m_mobile.ui.object;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ObjectActivity extends BaseActivity implements ObjectMvpView {

    @Inject
    ObjectMvpPresenter<ObjectMvpView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        mPresenter.onAttach(this);
    }
}
