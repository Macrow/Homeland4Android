package org.mstudio.homeland4android.ui.user_user_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import org.mstudio.homeland4android.HomelandApplication;
import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.di.component.DaggerUserUserListComponent;
import org.mstudio.homeland4android.di.module.UserUserListModule;
import org.mstudio.homeland4android.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static org.mstudio.homeland4android.ui.user_profile.UserProfileActivity.EXTRA_USER_LOGIN_NAME;

public class UserUserListActivity extends BaseActivity {

    public static final String EXTRA_USER_USER_LIST_TYPE = "EXTRA_USER_USER_LIST_TYPE";

    private String mUserLoginName;
    private String mUserListType;

    @Inject
    UserUserListPresenter mUserUserListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        mUserLoginName = getIntent().getStringExtra(EXTRA_USER_LOGIN_NAME);
        mUserListType = getIntent().getStringExtra(EXTRA_USER_USER_LIST_TYPE);
        if (mUserLoginName == null || mUserListType == null) {
            Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            finish();
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.contentBox);
        UserUserListFragment userUserListFragment = (UserUserListFragment) fragment;

        if (userUserListFragment == null) {
            userUserListFragment = UserUserListFragment.newInstance();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.contentBox, userUserListFragment);
            transaction.commit();
        }

        DaggerUserUserListComponent.builder()
                .basicComponent(((HomelandApplication) getApplication()).getBasicComponent())
                .userUserListModule(new UserUserListModule(mUserLoginName, mUserListType, userUserListFragment)).build()
                .inject(this);
    }

    @OnClick(R.id.btnBack)
    public void goBack() {
        finish();
    }
}
