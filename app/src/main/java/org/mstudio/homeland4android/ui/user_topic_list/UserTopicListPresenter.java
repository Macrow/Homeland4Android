package org.mstudio.homeland4android.ui.user_topic_list;

import org.mstudio.homeland4android.data.NoAuthorizedDataManager;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicListItem;
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
public class UserTopicListPresenter implements UserTopicListContract.Presenter {

    private String mUserLoginName;
    private String mTopicListType;
    private NoAuthorizedDataManager mNoAuthorizedDataManager;
    private UserTopicListContract.View mTopicsView;
    private CompositeDisposable mCompositeDisposable;
    private boolean mFirstTimeLoad = true;
    private boolean mLoadTopicListFinish = false;
    private int mOffset = 0;

    @Inject
    public UserTopicListPresenter(@Named("UserLoginName") String userLoginName,
                                  @Named("TopicListType") String topicListType,
                                  NoAuthorizedDataManager noAuthorizedDataManager,
                                  UserTopicListContract.View topicsView,
                                  CompositeDisposable compositeDisposable) {
        mUserLoginName = userLoginName;
        mTopicListType = topicListType;
        mNoAuthorizedDataManager = noAuthorizedDataManager;
        mTopicsView = topicsView;
        mCompositeDisposable = compositeDisposable;
    }

    @Inject
    public void setListener() {
        mTopicsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTopicList(true);
    }

    @Override
    public void finish() {
        mCompositeDisposable.dispose();
        mTopicsView = null;
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
        mNoAuthorizedDataManager.loadUserTopics(mUserLoginName, mTopicListType, mOffset, AppConstant.PAGE_LIMIT)
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
}
