package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.topic_add_edit.TopicAddEditContract;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/9
 * desc   :
 */
@Module
public class TopicAddEditModule {

    private TopicAddEditContract.View mView;
    private int mTopicId;

    public TopicAddEditModule(TopicAddEditContract.View view, int topicId) {
        mView = view;
        mTopicId = topicId;
    }

    @ActivityScope
    @Provides
    TopicAddEditContract.View provideTopicAddEditContractView() {
        return mView;
    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @ActivityScope
    @Provides
    int provideTopicId() {
        return mTopicId;
    }

}
