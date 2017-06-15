package org.mstudio.homeland4android.ui.nodes;

import org.mstudio.homeland4android.data.network.model.adjusted.NodeListItem;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
public interface NodesContract {

    interface View extends BaseView<Presenter> {

        void loadNodes(List<NodeListItem> nodeListItems);

    }

    interface Presenter extends BasePresenter {

        void loadNodes();

    }
}
