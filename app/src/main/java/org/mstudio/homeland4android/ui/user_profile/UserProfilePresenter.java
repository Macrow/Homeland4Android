package org.mstudio.homeland4android.ui.user_profile;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.NoAuthorizedDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.model.adjusted.User;
import org.mstudio.homeland4android.data.network.model.raw.RawActionResult;
import org.mstudio.homeland4android.util.LoginStateUtil;

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
 * time   : 2017/06/06
 * desc   :
 */
public class UserProfilePresenter implements UserProfileContract.Presenter {

    private UserProfileContract.View mUserProfileView;
    private NoAuthorizedDataManager mNoAuthorizedDataManager;
    private AuthorizedDataManager mAuthorizedDataManager;
    private TokenManager mTokenManager;
    private CompositeDisposable mCompositeDisposable;
    private String mUserLoginName;
    private boolean mIsBlocked;
    private boolean mIsFollowed;

    @Inject
    public UserProfilePresenter(String userLoginName,
                                UserProfileContract.View userProfileView,
                                NoAuthorizedDataManager noAuthorizedDataManager,
                                AuthorizedDataManager authorizedDataManager,
                                TokenManager tokenManager,
                                CompositeDisposable compositeDisposable) {
        mUserLoginName = userLoginName;
        mUserProfileView = userProfileView;
        mNoAuthorizedDataManager = noAuthorizedDataManager;
        mAuthorizedDataManager = authorizedDataManager;
        mTokenManager = tokenManager;
        mCompositeDisposable = compositeDisposable;
    }

    @Inject
    public void setListener() {
        mUserProfileView.setPresenter(this);
    }

    @Override
    public void start() {
        loadProfile();
    }

    @Override
    public void finish() {
        mUserProfileView = null;
        mCompositeDisposable.dispose();
    }

    @Override
    public String getUserLoginName() {
        return mUserLoginName;
    }

    @Override
    public void loadProfile() {
        mNoAuthorizedDataManager.loadUserProfile(mUserLoginName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<User>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mUserProfileView.showLoading();
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull User user) {
                    mUserProfileView.loadProfile(user);
                    if (mTokenManager.haveTokens()) {
                        mIsBlocked = user.mStatus.isBlocked();
                        mIsFollowed = user.mStatus.isFollowed();
                        mUserProfileView.loadActionView(user.mStatus.isBlocked(), user.mStatus.isFollowed());
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                    mUserProfileView.showError();
                }

                @Override
                public void onComplete() {
                    mUserProfileView.hideFeedbackBox();
                }
            });
    }

    @Override
    public void updateBlockUserStatus() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mUserProfileView)) {
            if (mIsBlocked) {
                mAuthorizedDataManager.unblockUser(mUserLoginName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mIsBlocked = false;
                                mUserProfileView.showMessage("取消屏蔽成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                mUserProfileView.showMessage("取消屏蔽失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } else {
                mAuthorizedDataManager.blockUser(mUserLoginName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mIsBlocked = true;
                                mUserProfileView.showMessage("屏蔽用户成功！");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                mUserProfileView.showMessage("屏蔽用户失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }
    }

    @Override
    public void updateFollowingUserStatus() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mUserProfileView)) {
            if (mIsFollowed) {
                mAuthorizedDataManager.unfollowUser(mUserLoginName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mIsFollowed = false;
                                mUserProfileView.showMessage("取消关注成功！");
                                mUserProfileView.updateUserFollowers("-1");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                mUserProfileView.showMessage("取消关注失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } else {
                mAuthorizedDataManager.followUser(mUserLoginName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RawActionResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mCompositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull RawActionResult rawActionResult) {
                                mIsFollowed = true;
                                mUserProfileView.showMessage("关注用户成功！");
                                mUserProfileView.updateUserFollowers("+1");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                mUserProfileView.showMessage("关注用户失败！");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }
    }
}
