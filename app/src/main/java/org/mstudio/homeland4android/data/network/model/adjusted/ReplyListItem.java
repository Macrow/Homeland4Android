package org.mstudio.homeland4android.data.network.model.adjusted;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.TimeUtils;
import com.squareup.picasso.Picasso;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.raw.RawReplies;
import org.mstudio.homeland4android.ui.topic_detail.ReplyViewHolder;
import org.mstudio.homeland4android.util.AppConstant;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.text.SimpleDateFormat;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class ReplyListItem extends AbstractFlexibleItem {

    public RawReplies.RepliesBean mReplyItem;

    public ReplyListItem(RawReplies.RepliesBean repliesBean) {
        mReplyItem = repliesBean;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_reply;
    }

    @Override
    public ReplyViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ReplyViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {
        ReplyViewHolder replyViewHolder = (ReplyViewHolder) holder;
        if (mReplyItem.getAction() != null || mReplyItem.isDeleted() || !mReplyItem.getAbilities().isUpdate()) {
            replyViewHolder.llAdminBox.setVisibility(View.GONE);
        }
        replyViewHolder.tvUserName.setText(mReplyItem.getUser().getLogin());
        replyViewHolder.tvReplyCreatedDateTime.setText("回复于 " + TimeUtils.getFriendlyTimeSpanByNow(mReplyItem.getCreated_at(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")));
        Picasso.with(replyViewHolder.ivUserImage.getContext())
                .load(mReplyItem.getUser().getAvatar_url())
                .placeholder(R.mipmap.noavatar)
                .into(replyViewHolder.ivUserImage);

        replyViewHolder.tvReplyNumber.setText("第" + String.valueOf(position) + "楼");

        String replyBody;
        if (mReplyItem.isDeleted()) {
            replyBody = "<p>*** 该回复已被删除 ***</p>";
        } else if (mReplyItem.getAction() != null) {
            switch (mReplyItem.getAction()) {
                case "ban":
                    replyBody = "<p>*** 内容不符合版规屏蔽此话题 ***</p>";
                    break;
                case "close":
                    replyBody = "<p>*** 关闭了讨论 ***</p>";
                    break;
                case "reopen":
                    replyBody = "<p>*** 重新开启了讨论 ***</p>";
                    break;
                case "excellent":
                    replyBody = "<p>*** 将本帖设为了精华贴 ***</p>";
                    break;
                case "mention":
                    replyBody = "<p>*** 在下帖中提及了此回复 ***</p>\n" +
                                "<a href='" + AppConstant.BASE_URL + "topics/" + mReplyItem.getMention_topic().getId() + "'>" +
                                mReplyItem.getMention_topic().getTitle() + "</a>\n";
                    break;
                case "unexcellent":
                    replyBody = "<p>*** 取消了精华贴 ***</p>";
                    break;
                default:
                    replyBody = "<p>*** 未知行为 ***</p>";
                    break;
            }
        } else {
            replyBody = mReplyItem.getBody_html();
        }
        replyBody = replyBody.replaceAll("<img title=\":[^:]+:\" alt=\"", "");
        replyBody = replyBody.replaceAll("\" src=\"https://twemoji[^\"]+\" class=\"twemoji\">", "");
        replyViewHolder.tvReplyContent.setHtml(replyBody, new HtmlHttpImageGetter(replyViewHolder.tvReplyContent, null, true));
    }
}
