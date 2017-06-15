package org.mstudio.homeland4android.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.mstudio.homeland4android.HomelandApplication;
import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.di.component.DaggerProfileComponent;
import org.mstudio.homeland4android.di.module.ProfileModule;
import org.mstudio.homeland4android.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    @Inject
    ProfilePresenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.contentBox);
        ProfileFragment profileFragment = (ProfileFragment) fragment;

        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.contentBox, profileFragment);
            transaction.commit();
        }

        DaggerProfileComponent.builder()
                .basicComponent(((HomelandApplication) getApplication()).getBasicComponent())
                .profileModule(new ProfileModule(profileFragment)).build()
                .inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mProfilePresenter.start();
        }
    }

    @OnClick(R.id.btnBack)
    public void goBack() {
        finish();
    }
}
