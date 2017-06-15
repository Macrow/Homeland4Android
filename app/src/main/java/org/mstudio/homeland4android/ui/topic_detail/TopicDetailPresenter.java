package org.mstudio.homeland4android.ui.topic_detail;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.NoAuthorizedDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.model.adjusted.ReplyListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicAndReplyListItem;
import org.mstudio.homeland4android.data.network.model.raw.RawActionResult;
import org.mstudio.homeland4android.data.network.model.raw.RawLikeResult;
import org.mstudio.homeland4android.util.AppConstant;
import org.mstudio.homeland4android.util.LoginStateUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class TopicDetailPresenter implements TopicDetailContract.Presenter {

    private NoAuthorizedDataManager mNoAuthorizedDataManager;
    private AuthorizedDataManager mAuthorizedDataManager;
    private TopicDetailContract.View mTopicDetailView;
    private TokenManager mTokenManager;
    private CompositeDisposable mCompositeDisposable;
    private int mTopicId;
    private boolean mTopicClosed;
    private boolean mTopicFavorite;
    private boolean mTopicLike;
    private boolean mTopicFollowing;
    private boolean mFirstTimeLoad = true;
    private boolean mLoadReplyListFinish = false;
    private int mOffset = 0;

    @Inject
    public TopicDetailPresenter(NoAuthorizedDataManager noAuthorizedDataManager,
                                AuthorizedDataManager authorizedDataManager,
                                TopicDetailContract.View view,
                                TokenManager tokenManager,
                                CompositeDisposable compositeDisposable,
                                int topicId) {
        mNoAuthorizedDataManager = noAuthorizedDataManager;
        mAuthorizedDataManager = authorizedDataManager;
        mTopicDetailView = view;
        mTokenManager = tokenManager;
        mCompositeDisposable = compositeDisposable;
        mTopicId = topicId;
    }

    @Inject
    public void setListener() {
        mTopicDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTopicAndReplyList();
    }

    @Override
    public void finish() {
        mCompositeDisposable.dispose();
        mTopicDetailView = null;
    }

    @Override
    public boolean isTopicClosed() {
        return mTopicClosed;
    }

    public void doActionOnTopic(String actionType) {
        final String msg;
        switch (actionType) {
            case "ban":
                msg = "屏蔽话题";
                break;
            case "excellent":
                msg = "加精华";
                break;
            case "unexcellent":
                msg = "去掉精华";
                break;
            case "close":
                msg = "关闭回复";
                break;
            case "open":
                msg = "开启回复";
                break;
            default:
                msg = "未知行为";
        }
        mAuthorizedDataManager.doActionOnTopic(mTopicId, actionType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RawActionResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RawActionResult rawActionResult) {
                        mTopicDetailView.showMessage(msg + "成功！");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mTopicDetailView.showMessage(msg + "失败！");
                    }

                    @Override
                    public void onComplete() {
                        start();
                    }
                });
    }

    public void loadTopicAndReplyList() {
        mOffset = 0;
        mLoadReplyListFinish = false;
        mNoAuthorizedDataManager.loadTopicAndReplyList(mTopicId, mOffset, AppConstant.PAGE_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TopicAndReplyListItem>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    if (mFirstTimeLoad) {
                        mTopicDetailView.showLoading();
                    }
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull TopicAndReplyListItem topicAndReplyListItem) {
                    mTopicDetailView.loadTopicAndReplyList(topicAndReplyListItem);
                    mTopicClosed = (topicAndReplyListItem.mTopicDetailItem.mTopicItem.getClosedAt() != null);
                    mTopicFavorite = (topicAndReplyListItem.mTopicDetailItem.mMetaBean.isFavorited());
                    mTopicLike = (topicAndReplyListItem.mTopicDetailItem.mMetaBean.isLiked());
                    mTopicFollowing = (topicAndReplyListItem.mTopicDetailItem.mMetaBean.isFollowed());
                    mTopicDetailView.initFavoriteAndFollowingButtonStatus(mTopicFavorite, mTopicLike, mTopicFollowing);
                    if (topicAndReplyListItem.mReplyListItem.size() < AppConstant.PAGE_LIMIT) {
                        mLoadReplyListFinish = true;
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                    mTopicDetailView.showError();
                }

                @Override
                public void onComplete() {
                    mTopicDetailView.hideFeedbackBox();
                    mFirstTimeLoad = false;
                }
            });
    }

    public void loadReplyList() {
        if (mLoadReplyListFinish) {
            return;
        }
        mOffset += AppConstant.PAGE_LIMIT;
        mNoAuthorizedDataManager.loadReplyList(mTopicId, mOffset, AppConstant.PAGE_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ReplyListItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<ReplyListItem> replyListItems) {
                        mTopicDetailView.loadReplyList(replyListItems);
                        if (replyListItems.size() < AppConstant.PAGE_LIMIT) {
                            mLoadReplyListFinish = true;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mTopicDetailView.showMessage("获取数据失败！");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateTopicFavoriteStatus() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mTopicDetailView)) {
            if (mTopicFavorite) {
                mAuthorizedDataManager.unfavoriteTopic(mTopicId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mTopicFavorite = false;
                                mTopicDetailView.showMessage("取消收藏成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mTopicDetailView.showMessage("取消收藏失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } else {
                mAuthorizedDataManager.favoriteTopic(mTopicId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mTopicFavorite = true;
                                mTopicDetailView.showMessage("收藏成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mTopicDetailView.showMessage("收藏失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }
    }

    public void updateTopicLikeStatus() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mTopicDetailView)) {
            if (mTopicLike) {
                mAuthorizedDataManager.unlikeTopic(mTopicId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawLikeResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawLikeResult rawLikeResult) {
                                mTopicLike = false;
                                mTopicDetailView.updateLikeCount(rawLikeResult.getCount());
                                mTopicDetailView.showMessage("取消点赞成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mTopicDetailView.showMessage("取消点赞失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } else {
                mAuthorizedDataManager.likeTopic(mTopicId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawLikeResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawLikeResult rawLikeResult) {
                                mTopicLike = true;
                                mTopicDetailView.updateLikeCount(rawLikeResult.getCount());
                                mTopicDetailView.showMessage("点赞成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mTopicDetailView.showMessage("点赞失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }
    }

    public void updateTopicFollowingStatus() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mTopicDetailView)) {
            if (mTopicFollowing) {
                mAuthorizedDataManager.unfollowTopic(mTopicId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mTopicFollowing = false;
                                mTopicDetailView.showMessage("取消关注成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mTopicDetailView.showMessage("取消关注失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } else {
                mAuthorizedDataManager.followTopic(mTopicId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mTopicFollowing = true;
                                mTopicDetailView.showMessage("关注成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mTopicDetailView.showMessage("关注失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }
    }
}
