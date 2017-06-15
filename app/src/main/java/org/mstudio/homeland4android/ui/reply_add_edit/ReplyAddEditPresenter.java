package org.mstudio.homeland4android.ui.reply_add_edit;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.model.adjusted.ReplyDetailItem;
import org.mstudio.homeland4android.data.network.model.raw.RawUploadPhoto;
import org.mstudio.homeland4android.util.LoginStateUtil;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/10
 * desc   :
 */
public class ReplyAddEditPresenter implements ReplyAddEditContract.Presenter {

    private AuthorizedDataManager mAuthorizedDataManager;
    private ReplyAddEditContract.View mReplyAddEditView;
    private TokenManager mTokenManager;
    private CompositeDisposable mCompositeDisposable;
    private int mTopicId;
    private int mReplyId;

    @Inject
    public ReplyAddEditPresenter(AuthorizedDataManager authorizedDataManager,
                                 ReplyAddEditContract.View view,
                                 TokenManager tokenManager,
                                 CompositeDisposable compositeDisposable,
                                 @Named("TopicId") int topicId,
                                 @Named("ReplyId") int replyId) {
        mAuthorizedDataManager = authorizedDataManager;
        mReplyAddEditView = view;
        mTokenManager = tokenManager;
        mCompositeDisposable = compositeDisposable;
        mTopicId = topicId;
        mReplyId = replyId;
    }

    @Inject
    public void setListener() {
        mReplyAddEditView.setPresenter(this);
    }

    @Override
    public void start() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mReplyAddEditView)) {
            if (mReplyId != 0) {
                mAuthorizedDataManager
                        .loadReply(mReplyId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ReplyDetailItem>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mReplyAddEditView.showLoading();
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull ReplyDetailItem ReplyDetailItem) {
                                mReplyAddEditView.loadReply(ReplyDetailItem);
                                mReplyAddEditView.startListenReplyChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                mReplyAddEditView.showError();
                            }

                            @Override
                            public void onComplete() {
                                mReplyAddEditView.hideFeedbackBox();
                            }
                        });
            } else {
                mReplyAddEditView.startListenReplyChanged();
            }
        }
    }

    @Override
    public void finish() {
        mReplyAddEditView = null;
        mCompositeDisposable.dispose();
    }

    public boolean isReplyDirty() {
        return mReplyAddEditView.isReplyDirty();
    }

    public void createReply() {
        if (mReplyAddEditView.validateReply()) {
            mAuthorizedDataManager
                    .createReply(mTopicId, mReplyAddEditView.getBody())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ReplyDetailItem>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mReplyAddEditView.showLoading();
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull ReplyDetailItem replyDetailItem) {
                            mReplyAddEditView.showMessage("回复成功");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            mReplyAddEditView.showError();
                        }

                        @Override
                        public void onComplete() {
                            mReplyAddEditView.finishActivity();
                        }
                    });
        }
    }

    public void updateReply() {
        if (mReplyAddEditView.validateReply()) {
            mAuthorizedDataManager
                    .updateReply(mReplyId, mReplyAddEditView.getBody())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ReplyDetailItem>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mReplyAddEditView.showLoading();
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull ReplyDetailItem replyDetailItem) {
                            mReplyAddEditView.showMessage("更新回复成功");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            mReplyAddEditView.showError();
                        }

                        @Override
                        public void onComplete() {
                            mReplyAddEditView.finishActivity();
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
                        mReplyAddEditView.showLoading();
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RawUploadPhoto rawUploadPhoto) {
                        mReplyAddEditView.uploadPhoto(rawUploadPhoto.getImage_url());
                        mReplyAddEditView.showMessage("上传文件成功！");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            if (httpException.code() == 413) {
                                mReplyAddEditView.showMessage("上传文件超过规定大小，上传失败！");
                            }
                        } else {
                            e.printStackTrace();
                            mReplyAddEditView.showMessage("上传文件失败！");
                        }
                    }

                    @Override
                    public void onComplete() {
                        mReplyAddEditView.hideFeedbackBox();
                    }
                });
    }
}
