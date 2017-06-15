package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.user_topic_list.UserTopicListContract;
import org.mstudio.homeland4android.ui.user_user_list.UserUserListContract;

import javax.inject.Named;

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
public class UserUserListModule {

    private String mUserLoginName;
    private String mUserListType;
    private UserUserListContract.View mView;

    public UserUserListModule(String userLoginName, String userListType, UserUserListContract.View view) {
        mUserLoginName = userLoginName;
        mUserListType = userListType;
        mView = view;
    }

    @ActivityScope
    @Named("UserLoginName")
    @Provides
    String provideUserLoginName() {
        return mUserLoginName;
    }

    @ActivityScope
    @Named("UserListType")
    @Provides
    String provideUserListType() {
        return mUserListType;
    }

    @ActivityScope
    @Provides
    UserUserListContract.View provideUserUserListView() {
        return mView;
    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
