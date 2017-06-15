package org.mstudio.homeland4android.ui.topic_add_edit;

import android.content.Context;

import org.mstudio.homeland4android.data.network.model.adjusted.TopicDetailItem;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/09
 * desc   :
 */

public interface TopicAddEditContract {

    interface View extends BaseView<Presenter> {

        boolean validateTopic();

        String getTitle();

        int getNodeId();

        String getBody();

        Context getViewContext();

        void loadTopic(TopicDetailItem topicDetailItem);

        boolean isTopicDirty();

        void startListenTopicChanged();

        void uploadPhoto(String url);

    }

    interface Presenter extends BasePresenter {

        void createTopic();

        void updateTopic();

        void uploadPhoto(String path);

    }
}
