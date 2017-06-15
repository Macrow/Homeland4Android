package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.di.notation.FragmentScope;
import org.mstudio.homeland4android.ui.nodes.NodesContract;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
@Module
public class NodesModule {

    private NodesContract.View mView;

    public NodesModule(NodesContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    NodesContract.View provideNodesContractView() {
        return mView;
    }

    @FragmentScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
