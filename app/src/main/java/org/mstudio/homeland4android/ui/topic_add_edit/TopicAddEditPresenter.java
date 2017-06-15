package org.mstudio.homeland4android.ui.topic_add_edit;

import android.content.Intent;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.data.network.model.raw.RawUploadPhoto;
import org.mstudio.homeland4android.ui.topic_detail.TopicDetailActivity;
import org.mstudio.homeland4android.util.LoginStateUtil;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static org.mstudio.homeland4android.ui.topic_detail.TopicDetailActivity.EXTRA_TOPIC_ID;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/09
 * desc   :
 */

public class TopicAddEditPresenter implements TopicAddEditContract.Presenter {

    private AuthorizedDataManager mAuthorizedDataManager;
    private TopicAddEditContract.View mTopicAddEditView;
    private TokenManager mTokenManager;
    private CompositeDisposable mCompositeDisposable;
    private int mTopicId;

    @Inject
    public TopicAddEditPresenter(AuthorizedDataManager authorizedDataManager,
                                 TopicAddEditContract.View view,
                                 TokenManager tokenManager,
                                 CompositeDisposable compositeDisposable,
                                 int topicId) {
        mAuthorizedDataManager = authorizedDataManager;
        mTopicAddEditView = view;
        mTokenManager = tokenManager;
        mCompositeDisposable = compositeDisposable;
        mTopicId = topicId;
    }

    @Inject
    public void setListener() {
        mTopicAddEditView.setPresenter(this);
    }

    @Override
    public void start() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mTopicAddEditView)) {
            if (mTopicId != 0) {
                mAuthorizedDataManager
                        .loadTopic(mTopicId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TopicDetailItem>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mTopicAddEditView.showLoading();
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull TopicDetailItem topicDetailItem) {
                                mTopicAddEditView.loadTopic(topicDetailItem);
                                mTopicAddEditView.startListenTopicChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                mTopicAddEditView.showError();
                            }

                            @Override
                            public void onComplete() {
                                mTopicAddEditView.hideFeedbackBox();
                            }
                        });
            } else {
                mTopicAddEditView.startListenTopicChanged();
            }
        }
    }

    public boolean isTopicDirty() {
        return mTopicAddEditView.isTopicDirty();
    }

    @Override
    public void finish() {
        mTopicAddEditView = null;
        mCompositeDisposable.dispose();
    }

    @Override
    public void createTopic() {
        if (mTopicAddEditView.validateTopic()) {
            mAuthorizedDataManager
                    .createTopic(mTopicAddEditView.getTitle(), mTopicAddEditView.getNodeId(), mTopicAddEditView.getBody())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TopicDetailItem>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mTopicAddEditView.showLoading();
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull TopicDetailItem topicDetailItem) {
                            mTopicId = topicDetailItem.mTopicItem.getId();
                            mTopicAddEditView.showMessage("创建话题成功");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            mTopicAddEditView.showError();
                        }

                        @Override
                        public void onComplete() {
                            Intent intent = new Intent(mTopicAddEditView.getViewContext(), TopicDetailActivity.class);
                            intent.putExtra(EXTRA_TOPIC_ID, mTopicId);
                            mTopicAddEditView.getViewContext().startActivity(intent);
                            mTopicAddEditView.finishActivity();
                        }
                    });
        }
    }

    @Override
    public void updateTopic() {
        if (mTopicAddEditView.validateTopic()) {
            mAuthorizedDataManager
                    .updateTopic(mTopicId, mTopicAddEditView.getTitle(), mTopicAddEditView.getNodeId(), mTopicAddEditView.getBody())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TopicDetailItem>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mTopicAddEditView.showLoading();
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull TopicDetailItem topicDetailItem) {
                            mTopicId = topicDetailItem.mTopicItem.getId();
                            mTopicAddEditView.showMessage("更新话题成功");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            mTopicAddEditView.showError();
                        }

                        @Override
                        public void onComplete() {
                            mTopicAddEditView.finishActivity();
                        }
                    });
        }
    }

    @Override
    public void uploadPhoto(String path) {
        mAuthorizedDataManager.uploadPhoto(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RawUploadPhoto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mTopicAddEditView.showLoading();
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RawUploadPhoto rawUploadPhoto) {
                        mTopicAddEditView.uploadPhoto(rawUploadPhoto.getImage_url());
                        mTopicAddEditView.showMessage("上传文件成功！");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            if (httpException.code() == 413) {
                                mTopicAddEditView.showMessage("上传文件超过规定大小，上传失败！");
                            }
                        } else {
                            e.printStackTrace();
                            mTopicAddEditView.showMessage("上传文件失败！");
                        }
                    }

                    @Override
                    public void onComplete() {
                        mTopicAddEditView.hideFeedbackBox();
                    }
                });
    }
}
