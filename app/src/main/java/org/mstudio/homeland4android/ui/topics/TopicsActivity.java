package org.mstudio.homeland4android.ui.topics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.iconics.view.IconicsImageView;

import org.mstudio.homeland4android.HomelandApplication;
import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.di.component.DaggerNodesComponent;
import org.mstudio.homeland4android.di.component.DaggerTopicsComponent;
import org.mstudio.homeland4android.di.module.NodesModule;
import org.mstudio.homeland4android.di.module.TopicsModule;
import org.mstudio.homeland4android.ui.about.AboutActivity;
import org.mstudio.homeland4android.ui.base.BaseActivity;
import org.mstudio.homeland4android.ui.base.DialogCaller;
import org.mstudio.homeland4android.ui.nodes.NodesDialogFragment;
import org.mstudio.homeland4android.ui.profile.ProfileActivity;
import org.mstudio.homeland4android.ui.topic_add_edit.TopicAddEditActivity;
import org.mstudio.homeland4android.util.FastClickUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicsActivity extends BaseActivity implements DialogCaller, PopupMenu.OnMenuItemClickListener {

    private static final String KEY_FILTER_TYPE = "KEY_FILTER_TYPE";
    private static final String KEY_NODE_ID = "KEY_NODE_ID";

    private String mFilterType = "last_actived";
    private int mMenuIndex = 0;
    private String mNodeId = "";
    private long mFirstTimeOnBackKey = 0;

    private PopupMenu mFilterMenu;

    @BindView(R.id.btnTopicFilter)
    IconicsImageView btnTopicFilter;
    @BindView(R.id.btnUser)
    UserWithNumbersView btnUser;

    @Inject
    TopicsPresenter mTopicsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mFilterType = savedInstanceState.getString(KEY_FILTER_TYPE, "last_actived");
            mNodeId = savedInstanceState.getString(KEY_NODE_ID, "");
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.contentBox);
        TopicsFragment topicsFragment = (TopicsFragment) fragment;

        if (topicsFragment == null) {
            topicsFragment = TopicsFragment.newInstance();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.contentBox, topicsFragment);
            transaction.commit();
        }

        DaggerTopicsComponent.builder()
                .basicComponent(((HomelandApplication) getApplication()).getBasicComponent())
                .topicsModule(new TopicsModule(topicsFragment, mFilterType, mNodeId)).build()
                .inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FILTER_TYPE, mFilterType);
        outState.putString(KEY_NODE_ID, mNodeId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mTopicsPresenter.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFilterMenu != null) {
            mFilterMenu.dismiss();
        }
    }

    @OnClick(R.id.btnUser)
    public void loadUserActivity() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @OnClick(R.id.btnTopicFilter)
    public void changeTopicFilter() {
        if (!FastClickUtil.isFastClick()) {
            mFilterMenu = new PopupMenu(this, btnTopicFilter);
            MenuInflater inflater = mFilterMenu.getMenuInflater();
            inflater.inflate(R.menu.topic_filter_type, mFilterMenu.getMenu());
            mFilterMenu.getMenu().getItem(mMenuIndex).setEnabled(false);
            mFilterMenu.setOnMenuItemClickListener(this);
            mFilterMenu.show();
        }
    }

    @OnClick(R.id.btnNewTopic)
    public void loadTopicAddEditActivity() {
        if (!FastClickUtil.isFastClick()) {
            Intent intent = new Intent(getApplicationContext(), TopicAddEditActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @OnClick(R.id.btnNode)
    public void loadNodesDialog() {
        if (!FastClickUtil.isFastClick()) {
            NodesDialogFragment nodesDialogFragment = new NodesDialogFragment();
            DaggerNodesComponent.builder()
                    .basicComponent(((HomelandApplication) getApplication()).getBasicComponent())
                    .nodesModule(new NodesModule(nodesDialogFragment)).build()
                    .inject(nodesDialogFragment);
            nodesDialogFragment.setCaller(this);
            nodesDialogFragment.show(getSupportFragmentManager(), null);
        }
    }

    @OnClick(R.id.btnRefresh)
    public void refreshActivity() {
        mTopicsPresenter.start();
    }

    @Override
    public void OnItemClick(int id, String name) {
        if (id == 0) {
            mNodeId = "";
        } else {
            mNodeId = String.valueOf(id);
        }
        mTopicsPresenter.changeNodeId(mNodeId);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.last_actived:
                mFilterType = "last_actived";
                mMenuIndex = 0;
                break;
            case R.id.recent:
                mFilterType = "recent";
                mMenuIndex = 1;
                break;
            case R.id.no_reply:
                mFilterType = "no_reply";
                mMenuIndex = 2;
                break;
            case R.id.popular:
                mFilterType = "popular";
                mMenuIndex = 3;
                break;
            case R.id.excellent:
                mFilterType = "excellent";
                mMenuIndex = 4;
                break;
            case  R.id.about:
                startAboutActivity();
                break;
            default:
                mFilterType = "last_actived";
                mMenuIndex = 0;
                break;
        }
        mTopicsPresenter.changeTopicFilterType(mFilterType);
        return false;
    }

    public void updateUnreadNotification(int count) {
        btnUser.updateNumber(count);
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - mFirstTimeOnBackKey > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mFirstTimeOnBackKey = secondTime;
        } else {
            super.onBackPressed();
        }
    }

    private void startAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
