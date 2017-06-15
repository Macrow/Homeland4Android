package org.mstudio.homeland4android.ui.user_user_list;

import org.mstudio.homeland4android.data.NoAuthorizedDataManager;
import org.mstudio.homeland4android.data.network.model.adjusted.UserListItem;
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
public class UserUserListPresenter implements UserUserListContract.Presenter {

    private String mUserLoginName;
    private String mUserListType;
    private NoAuthorizedDataManager mNoAuthorizedDataManager;
    private UserUserListContract.View mUsersView;
    private CompositeDisposable mCompositeDisposable;
    private boolean mFirstTimeLoad = true;
    private boolean mLoadUserListFinish = false;
    private int mOffset = 0;

    @Inject
    public UserUserListPresenter(@Named("UserLoginName") String userLoginName,
                                 @Named("UserListType") String userListType,
                                 NoAuthorizedDataManager noAuthorizedDataManager,
                                 UserUserListContract.View usersView,
                                 CompositeDisposable compositeDisposable) {
        mUserLoginName = userLoginName;
        mUserListType = userListType;
        mNoAuthorizedDataManager = noAuthorizedDataManager;
        mUsersView = usersView;
        mCompositeDisposable = compositeDisposable;
    }

    @Inject
    public void setListener() {
        mUsersView.setPresenter(this);
    }

    @Override
    public void start() {
        loadUserList(true);
    }

    @Override
    public void finish() {
        mCompositeDisposable.dispose();
        mUsersView = null;
    }

    @Override
    public void loadUserList(final boolean reload) {
        if (reload) {
            mOffset = 0;
            mLoadUserListFinish = false;
        } else if (mLoadUserListFinish) {
                return;
        } else {
            mOffset += AppConstant.PAGE_LIMIT;
        }
        mNoAuthorizedDataManager.loadUserList(mUserLoginName, mUserListType, mOffset, AppConstant.PAGE_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserListItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (mFirstTimeLoad) {
                            mUsersView.showLoading();
                        }
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<UserListItem> UserListItems) {
                        mUsersView.loadUserList(UserListItems, reload);
                        if (UserListItems.size() < AppConstant.PAGE_LIMIT) {
                            mLoadUserListFinish = true;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mUsersView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mUsersView.hideFeedbackBox();
                        mFirstTimeLoad = false;
                    }
                });
    }
}
