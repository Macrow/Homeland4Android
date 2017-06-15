package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.reply_add_edit.ReplyAddEditContract;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/10
 * desc   :
 */
@Module
public class ReplyAddEditModule {

    private ReplyAddEditContract.View mView;
    private int mTopicId;
    private int mReplyId;

    public ReplyAddEditModule(ReplyAddEditContract.View view, int topicId, int replyId) {
        mView = view;
        mTopicId = topicId;
        mReplyId = replyId;
    }

    @ActivityScope
    @Provides
    ReplyAddEditContract.View provideReplyAddEditContractView() {
        return mView;
    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @ActivityScope
    @Named("TopicId")
    @Provides
    int provideTopicId() {
        return mTopicId;
    }

    @ActivityScope
    @Named("ReplyId")
    @Provides
    int provideReplyId() {
        return mReplyId;
    }
}
