package org.mstudio.homeland4android.ui.nodes;

import org.mstudio.homeland4android.data.NoAuthorizedDataManager;
import org.mstudio.homeland4android.data.network.model.adjusted.NodeListItem;

import java.util.List;

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
 * time   : 2017/06/07
 * desc   :
 */
public class NodesPresenter implements NodesContract.Presenter {

    private NoAuthorizedDataManager mNoAuthorizedDataManager;
    private NodesContract.View mNodesView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public NodesPresenter(NoAuthorizedDataManager noAuthorizedDataManager, NodesContract.View view, CompositeDisposable compositeDisposable) {
        mNoAuthorizedDataManager = noAuthorizedDataManager;
        mNodesView = view;
        mCompositeDisposable = compositeDisposable;
    }

    @Inject
    public void setListener() {
        mNodesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadNodes();
    }

    @Override
    public void finish() {
        mNodesView = null;
        mCompositeDisposable.dispose();
    }

    @Override
    public void loadNodes() {
        mNoAuthorizedDataManager.loadNodeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NodeListItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mNodesView.showLoading();
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<NodeListItem> nodeListItems) {
                        mNodesView.loadNodes(nodeListItems);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mNodesView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mNodesView.hideFeedbackBox();
                    }
                });
    }
}
