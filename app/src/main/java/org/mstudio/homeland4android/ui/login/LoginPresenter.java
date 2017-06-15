package org.mstudio.homeland4android.ui.login;

import android.widget.Toast;

import org.mstudio.homeland4android.data.OAuthDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.model.raw.RawToken;

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
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private OAuthDataManager mOAuthDataManager;
    private TokenManager mTokenManager;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public LoginPresenter(LoginContract.View view, TokenManager tokenManager, OAuthDataManager oAuthDataManager, CompositeDisposable compositeDisposable) {
        mView = view;
        mTokenManager = tokenManager;
        mOAuthDataManager = oAuthDataManager;
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {
        mCompositeDisposable.dispose();
        mView = null;
    }

    @Override
    public void LoginByAuthCode(String code) {
        mOAuthDataManager.LoginByAuthCode(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<RawToken>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull RawToken rawToken) {
                    mTokenManager.updateTokens(rawToken.getAccessToken(), rawToken.getRefreshToken());
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Toast.makeText(mView.getContext(), "验证信息已经过期，无法登陆！", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onComplete() {
                    mView.finishActivity();
                }
            });
    }
}
