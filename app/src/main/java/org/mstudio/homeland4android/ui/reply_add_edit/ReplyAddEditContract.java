package org.mstudio.homeland4android.ui.reply_add_edit;

import org.mstudio.homeland4android.data.network.model.adjusted.ReplyDetailItem;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/10
 * desc   :
 */
public interface ReplyAddEditContract {

    interface View extends BaseView<Presenter> {

        boolean validateReply();

        String getBody();

        void loadReply(ReplyDetailItem replyDetailItem);

        boolean isReplyDirty();

        void startListenReplyChanged();

        void uploadPhoto(String url);

    }

    interface Presenter extends BasePresenter {

        void createReply();

        void updateReply();

        void uploadPhoto(String path);

    }
}
