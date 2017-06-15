package org.mstudio.homeland4android.data.network.model.adjusted;

import org.mstudio.homeland4android.data.network.model.raw.RawReply;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/10
 * desc   :
 */
public class ReplyDetailItem {

    public RawReply.ReplyBean mReplyItem;

    public ReplyDetailItem(RawReply.ReplyBean replyBean) {
        mReplyItem = replyBean;
    }
}
