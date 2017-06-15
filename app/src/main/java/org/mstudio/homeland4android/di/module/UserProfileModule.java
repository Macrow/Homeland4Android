package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.user_profile.UserProfileContract;

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
public class UserProfileModule {

    private String mUserLoginName;
    private UserProfileContract.View mView;

    public UserProfileModule(String userLoginName, UserProfileContract.View view) {
        mUserLoginName = userLoginName;
        mView = view;
    }

    @ActivityScope
    @Provides
    String provideUserLoginName() {
        return mUserLoginName;
    }

    @ActivityScope
    @Provides
    UserProfileContract.View provideUserProfileView() {
        return mView;
    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
