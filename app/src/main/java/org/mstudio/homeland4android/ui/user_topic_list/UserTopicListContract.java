package org.mstudio.homeland4android.ui.user_topic_list;

import org.mstudio.homeland4android.data.network.model.adjusted.TopicListItem;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public interface UserTopicListContract {

    interface View extends BaseView<Presenter> {

        void loadTopicList(List<TopicListItem> topics, boolean reload);

    }

    interface Presenter extends BasePresenter {

        void loadTopicList(boolean reload);

    }
}
