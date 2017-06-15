package org.mstudio.homeland4android.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.mstudio.homeland4android.HomelandApplication;
import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.di.component.DaggerLoginComponent;
import org.mstudio.homeland4android.di.module.LoginModule;
import org.mstudio.homeland4android.ui.base.BaseActivity;
import org.mstudio.homeland4android.ui.topics.TopicsActivity;
import org.mstudio.homeland4android.util.AppConstant;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                .basicComponent(((HomelandApplication) getApplication()).getBasicComponent())
                .loginModule(new LoginModule(this)).build()
                .inject(this);

        Uri uri = getIntent().getData();
        if (uri != null) {
            String code = uri.getQueryParameter("code");
            mPresenter.LoginByAuthCode(code);
        }
    }

    @OnClick(R.id.btnLoginByAuthCode)
    public void LoginByAuthCode() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri loginUrl = Uri.parse(AppConstant.OAUTH_LOGIN_URI);
        intent.setData(loginUrl);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.finish();
    }

    @Override
    public void finishActivity() {
        Intent intent = new Intent(getContext(), TopicsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {}

    @Override
    public void showLoading() {}

    @Override
    public void showError() {}

    @Override
    public void hideFeedbackBox() {}

    @Override
    public void showMessage(String msg) {}
}
