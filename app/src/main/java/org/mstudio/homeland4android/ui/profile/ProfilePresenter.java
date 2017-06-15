package org.mstudio.homeland4android.ui.profile;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.model.adjusted.User;
import org.mstudio.homeland4android.data.network.model.raw.RawUnreadNotificationResult;
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
public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View mProfileView;
    private AuthorizedDataManager mAuthorizedDataManager;
    private TokenManager mTokenManager;
    private CompositeDisposable mCompositeDisposable;
    private String mProfileLoginName;

    @Inject
    public ProfilePresenter(ProfileContract.View profleView,
                            AuthorizedDataManager authorizedDataManager,
                            TokenManager tokenManager,
                            CompositeDisposable compositeDisposable) {
        mProfileView = profleView;
        mAuthorizedDataManager = authorizedDataManager;
        mTokenManager = tokenManager;
        mCompositeDisposable = compositeDisposable;
    }

    @Inject
    public void setListener() {
        mProfileView.setPresenter(this);
    }

    @Override
    public void start() {
        if (LoginStateUtil.checkLoginState(mTokenManager, mProfileView)) {
            loadProfile();
        }
    }

    @Override
    public void finish() {
        mProfileView = null;
        mCompositeDisposable.dispose();
    }

    @Override
    public void loadProfile() {
        mAuthorizedDataManager.loadMyProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<User>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mProfileView.showLoading();
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull User user) {
                    mProfileView.loadProfile(user);
                    mProfileLoginName = user.mUser.getLogin();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                    mProfileView.showError();
                }

                @Override
                public void onComplete() {
                    mProfileView.hideFeedbackBox();
                }
            });
        mAuthorizedDataManager.loadUnreadNotificationCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RawUnreadNotificationResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RawUnreadNotificationResult rawUnreadNotificationResult) {
                        mProfileView.updateUnreadNotificationCount(rawUnreadNotificationResult.getCount());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mProfileView.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void Logout() {
        mTokenManager.Logout();
        mProfileView.finishActivity();
    }

    @Override
    public String getProfileLoginName() {
        return mProfileLoginName;
    }
}
