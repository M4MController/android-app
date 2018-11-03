package com.m4m.lieroz.m4m_mobile.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;
import com.m4m.lieroz.m4m_mobile.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthActivity extends BaseActivity implements AuthMvpView {

    @Inject
    AuthMvpPresenter<AuthMvpView> mPresenter;

    @BindView(R.id.email_field)
    EditText mEmailField;

    @BindView(R.id.password_field)
    EditText mPasswordField;

    @BindView(R.id.login_button)
    Button mLoginButton;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, AuthActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();
                mPresenter.onLoginClick(email, password);
            }
        });
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

    @Override
    public void openMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
