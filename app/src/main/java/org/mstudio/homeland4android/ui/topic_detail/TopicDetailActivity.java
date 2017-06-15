package org.mstudio.homeland4android.ui.topic_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.sackcentury.shinebuttonlib.ShineButton;

import org.mstudio.homeland4android.HomelandApplication;
import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.di.component.DaggerTopicDetailComponent;
import org.mstudio.homeland4android.di.module.TopicDetailModule;
import org.mstudio.homeland4android.ui.base.AlertDialogCaller;
import org.mstudio.homeland4android.ui.base.BaseActivity;
import org.mstudio.homeland4android.ui.reply_add_edit.ReplyAddEditActivity;
import org.mstudio.homeland4android.util.AlertDialogUtil;
import org.mstudio.homeland4android.util.FastClickUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicDetailActivity extends BaseActivity implements AlertDialogCaller {

    public static final String EXTRA_TOPIC_ID = "EXTRA_TOPIC_ID";

    private int mTopicId;
    private String mActionType;

    @BindView(R.id.btnFavorite)
    ShineButton btnFavorite;
    @BindView(R.id.btnLike)
    ShineButton btnLike;
    @BindView(R.id.btnFollowing)
    ShineButton btnFollowing;

    @Inject
    TopicDetailPresenter mTopicDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);
        mTopicId = getIntent().getIntExtra(EXTRA_TOPIC_ID, 0);
        if (mTopicId == 0) {
            Toast.makeText(getApplicationContext(), "获取话题失败", Toast.LENGTH_SHORT).show();
            finish();
        }


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.contentBox);
        TopicDetailFragment topicDetailFragment = (TopicDetailFragment) fragment;
        if (topicDetailFragment == null) {
            topicDetailFragment = TopicDetailFragment.newInstance();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.contentBox, topicDetailFragment);
            transaction.commit();
        }

        DaggerTopicDetailComponent.builder()
                .basicComponent(((HomelandApplication) getApplication()).getBasicComponent())
                .topicDetailModule(new TopicDetailModule(topicDetailFragment, mTopicId)).build()
                .inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mTopicDetailPresenter.loadTopicAndReplyList();
        }
    }

    @OnClick(R.id.btnBack)
    public void goBack() {
        finish();
    }

    @OnClick(R.id.btnFavorite)
    public void updateTopicFavoriteStatus() {
        mTopicDetailPresenter.updateTopicFavoriteStatus();
    }

    @OnClick(R.id.btnLike)
    public void updateTopicLikeStatus() {
        mTopicDetailPresenter.updateTopicLikeStatus();
    }

    @OnClick(R.id.btnFollowing)
    public void updateTopicFollowingStatus() {
        mTopicDetailPresenter.updateTopicFollowingStatus();
    }

    public void doActionOnTopic(String actionType) {
        mActionType = actionType;
        AlertDialogUtil.buildDialog(this, this).show();
    }

    public void initFavoriteAndFollowingButtonStatus(boolean isFavorite, boolean isLike, boolean isFollowing) {
        btnFavorite.setChecked(isFavorite);
        btnLike.setChecked(isLike);
        btnFollowing.setChecked(isFollowing);
    }

    @OnClick(R.id.btnNewReply)
    public void loadReplyAddEditActivity() {
        if (!FastClickUtil.isFastClick()) {
            if (mTopicDetailPresenter.isTopicClosed()) {
                Toast.makeText(this, "该话题已关闭，不能回复！", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), ReplyAddEditActivity.class);
                intent.putExtra(EXTRA_TOPIC_ID, mTopicId);
                startActivityForResult(intent, 0);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void OnConfirmClick() {
        mTopicDetailPresenter.doActionOnTopic(mActionType);
    }
}
