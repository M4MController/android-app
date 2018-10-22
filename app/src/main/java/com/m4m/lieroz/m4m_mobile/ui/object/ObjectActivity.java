package com.m4m.lieroz.m4m_mobile.ui.object;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Sensor;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ObjectActivity extends BaseActivity implements ObjectMvpView {

    @Inject
    ObjectMvpPresenter<ObjectMvpView> mPresenter;

    @Inject
    ObjectAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();

        mPresenter.useDataManager();
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

        Intent intent = getIntent();
        mToolbar.setTitle(intent.getStringExtra("title"));
        address = intent.getStringExtra("address");
        mToolbar.setSubtitle(address);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mAdapter.setAddress(address);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void update(List<Sensor> sensors) {
        mAdapter.setUserSensorsResponseList(sensors);
    }
}
