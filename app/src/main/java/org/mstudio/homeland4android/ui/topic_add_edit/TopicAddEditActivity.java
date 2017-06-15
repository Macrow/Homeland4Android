package org.mstudio.homeland4android.ui.topic_add_edit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.orhanobut.logger.Logger;

import org.mstudio.homeland4android.HomelandApplication;
import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.di.component.DaggerTopicAddEditComponent;
import org.mstudio.homeland4android.di.module.TopicAddEditModule;
import org.mstudio.homeland4android.ui.base.AlertDialogCaller;
import org.mstudio.homeland4android.ui.base.BaseActivity;
import org.mstudio.homeland4android.util.AlertDialogUtil;
import org.mstudio.homeland4android.util.AppConstant;
import org.mstudio.homeland4android.util.FastClickUtil;
import org.mstudio.homeland4android.util.ImageGetterUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static org.mstudio.homeland4android.util.AppConstant.CAMERA_REQUEST_CODE;

public class TopicAddEditActivity extends BaseActivity implements AlertDialogCaller {

    public static final String EXTRA_TOPIC_ID = "EXTRA_TOPIC_ID";
    public static final String EXTRA_CAMERA_IMAGE_PATH = "EXTRA_TOPIC_ID";

    private int mTopicId;
    private String mCameraPath;

    @Inject
    TopicAddEditPresenter mTopicAddEditPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_add_edit);
        ButterKnife.bind(this);
        mTopicId = getIntent().getIntExtra(EXTRA_TOPIC_ID, 0);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.contentBox);
        TopicAddEditFragment topicAddEditFragment = (TopicAddEditFragment) fragment;
        if (topicAddEditFragment == null) {
            topicAddEditFragment = TopicAddEditFragment.newInstance(mTopicId);
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.contentBox, topicAddEditFragment);
            transaction.commit();
        }

        DaggerTopicAddEditComponent.builder()
                .basicComponent(((HomelandApplication) getApplication()).getBasicComponent())
                .topicAddEditModule(new TopicAddEditModule(topicAddEditFragment, mTopicId)).build()
                .inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstant.ALBUM_REQUEST_CODE) {
                Uri uri = data.getData();
                if (uri != null) {
                    String absolutePath= ImageGetterUtil.getAbsolutePath(this, uri);
                    mTopicAddEditPresenter.uploadPhoto(absolutePath);
                }
            } else if (requestCode == CAMERA_REQUEST_CODE) {
                if (mCameraPath != null) {
                    mTopicAddEditPresenter.uploadPhoto(mCameraPath);
                }
            }
        }
    }

    @OnClick(R.id.btnBack)
    public void goBack() {
        if (!FastClickUtil.isFastClick()) {
            if (mTopicAddEditPresenter.isTopicDirty()) {
                AlertDialogUtil.buildDialog(this, this).show();
            } else {
                finish();
            }
        }
    }

    @OnClick(R.id.btnCreateOrUpdate)
    public void createOrUpdateTopic() {
        if (!FastClickUtil.isFastClick()) {
            if (mTopicId == 0) {
                mTopicAddEditPresenter.createTopic();
            } else {
                mTopicAddEditPresenter.updateTopic();
            }
        }
    }

    @OnClick(R.id.btnCamera)
    public void loadImageFromCamera() {
        mCameraPath = ImageGetterUtil.openCamera(this);
    }

    @OnClick(R.id.btnGallery)
    public void loadImageFromGallery() {
        ImageGetterUtil.openGallery(this);
    }

    @Override
    public void OnConfirmClick() {
        finish();
    }
}
