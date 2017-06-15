package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.topics.TopicsContract;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
@Module
public class TopicsModule {

    private TopicsContract.View mView;
    private String mFilterType;
    private String mNodeId;

    public TopicsModule(TopicsContract.View view, String filterType, String nodeId) {
        mView = view;
        mFilterType = filterType;
        mNodeId = nodeId;
    }

    @ActivityScope
    @Provides
    TopicsContract.View provideTopicsContractView() {
        return mView;
    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @ActivityScope
    @Named("FilterType")
    @Provides
    String provideFilterType() {
        return mFilterType;
    }

    @ActivityScope
    @Named("NodeId")
    @Provides
    String provideNodeId() {
        return mNodeId;
    }
}
