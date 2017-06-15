package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.login.LoginContract;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
@Module
public class LoginModule {

    private LoginContract.View mView;

    public LoginModule(LoginContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View provideLoginContractView() {
        return mView;
    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
