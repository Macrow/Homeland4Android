package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.topic_detail.TopicDetailContract;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
@Module
public class TopicDetailModule {

    private TopicDetailContract.View mView;
    private int mTopicId;

    public TopicDetailModule(TopicDetailContract.View view, int topicId) {
        mView = view;
        mTopicId = topicId;
    }

    @ActivityScope
    @Provides
    TopicDetailContract.View provideTopicDetailContractView() {
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
