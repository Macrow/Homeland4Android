package org.mstudio.homeland4android.ui.topic_detail;

import org.mstudio.homeland4android.data.network.model.adjusted.ReplyListItem;
import org.mstudio.homeland4android.data.network.model.adjusted.TopicAndReplyListItem;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public interface TopicDetailContract {

    interface View extends BaseView<Presenter> {

        void loadTopicAndReplyList(TopicAndReplyListItem topicAndReplyListItem);

        void loadReplyList(List<ReplyListItem> replyListItems);

        void initFavoriteAndFollowingButtonStatus(boolean isFavorite, boolean isLike, boolean isFollowing);

        void updateLikeCount(int count);

    }

    interface Presenter extends BasePresenter {

        void loadTopicAndReplyList();

        void loadReplyList();

        boolean isTopicClosed();

    }
}
