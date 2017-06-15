package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.user_topic_list.UserTopicListContract;

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
public class UserTopicListModule {

    private String mUserLoginName;
    private String mTopicListType;
    private UserTopicListContract.View mView;

    public UserTopicListModule(String userLoginName, String topicListType, UserTopicListContract.View view) {
        mUserLoginName = userLoginName;
        mTopicListType = topicListType;
        mView = view;
    }

    @ActivityScope
    @Named("UserLoginName")
    @Provides
    String provideUserLoginName() {
        return mUserLoginName;
    }

    @ActivityScope
    @Named("TopicListType")
    @Provides
    String provideTopicListType() {
        return mTopicListType;
    }

    @ActivityScope
    @Provides
    UserTopicListContract.View provideUserTopicListView() {
        return mView;
    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
