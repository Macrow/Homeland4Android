package org.mstudio.homeland4android.ui.topics;

import com.orhanobut.logger.Logger;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.NoAuthorizedDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicListItem;
import org.mstudio.homeland4android.data.network.model.raw.RawUnreadNotificationResult;
import org.mstudio.homeland4android.util.AppConstant;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public class TopicsPresenter implements TopicsContract.Presenter {

    private NoAuthorizedDataManager mNoAuthorizedDataManager;
    private AuthorizedDataManager mAuthorizedDataManager;
    private TokenManager mTokenManager;
    private TopicsContract.View mTopicsView;
    private CompositeDisposable mCompositeDisposable;
    private boolean mFirstTimeLoad = true;
    private boolean mLoadTopicListFinish = false;
    private String mNodeId;
    private String mFilterType;
    private int mOffset = 0;

    @Inject
    public TopicsPresenter(NoAuthorizedDataManager noAuthorizedDataManager,
                           AuthorizedDataManager authorizedDataManager,
                           TokenManager tokenManager,
                           TopicsContract.View topicsView,
                           @Named("FilterType") String filterType,
                           @Named("NodeId") String nodeId,
                           CompositeDisposable compositeDisposable) {
        mNoAuthorizedDataManager = noAuthorizedDataManager;
        mAuthorizedDataManager = authorizedDataManager;
        mTokenManager = tokenManager;
        mTopicsView = topicsView;
        mFilterType = filterType;
        mNodeId = nodeId;
        mCompositeDisposable = compositeDisposable;
    }

    @Inject
    public void setListener() {
        mTopicsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTopicList(true);
        updateUnreadNotification();
    }

    @Override
    public void finish() {
        mCompositeDisposable.dispose();
        mTopicsView = null;
    }

    public void changeNodeId(String id) {
        mNodeId = id;
        loadTopicList(true);
    }

    public void changeTopicFilterType(String type) {
        mFilterType = type;
        loadTopicList(true);
    }

    @Override
    public void loadTopicList(final boolean reload) {
        if (reload) {
            mOffset = 0;
            mLoadTopicListFinish = false;
        } else if (mLoadTopicListFinish) {
                return;
        } else {
            mOffset += AppConstant.PAGE_LIMIT;
        }
        mNoAuthorizedDataManager.loadTopicList(mNodeId, mFilterType, mOffset, AppConstant.PAGE_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TopicListItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (mFirstTimeLoad) {
                            mTopicsView.showLoading();
                        }
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<TopicListItem> topicListItems) {
                        mTopicsView.loadTopicList(topicListItems, reload);
                        if (topicListItems.size() < AppConstant.PAGE_LIMIT) {
                            mLoadTopicListFinish = true;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mTopicsView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mTopicsView.hideFeedbackBox();
                        mFirstTimeLoad = false;
                    }
                });
    }

    private void updateUnreadNotification() {
        if(mTokenManager.haveTokens()) {
            mAuthorizedDataManager.loadUnreadNotificationCountNoForceLogin()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RawUnreadNotificationResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull RawUnreadNotificationResult rawUnreadNotificationResult) {
                            mTopicsView.updateUnreadNotification(rawUnreadNotificationResult.getCount());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            mTopicsView.updateUnreadNotification(0);
        }
    }
}
