package org.mstudio.homeland4android.data.network.model.adjusted;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.TimeUtils;
import com.squareup.picasso.Picasso;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.raw.RawTopic;
import org.mstudio.homeland4android.ui.topic_detail.TopicDetailViewHolder;
import org.mstudio.homeland4android.util.MarkDownStyle;

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
public class TopicDetailItem extends AbstractFlexibleItem {

    public RawTopic.TopicBean mTopicItem;
    public RawTopic.MetaBean mMetaBean;

    public TopicDetailItem(RawTopic.TopicBean topicBean, RawTopic.MetaBean metaBean) {
        mTopicItem = topicBean;
        mMetaBean = metaBean;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_topic_detail;
    }

    @Override
    public TopicDetailViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new TopicDetailViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {
        final TopicDetailViewHolder topicDetailViewHolder = (TopicDetailViewHolder) holder;
        Context context = topicDetailViewHolder.itemView.getContext();

        if (mTopicItem.getAbilities().isUpdate() || mTopicItem.getAbilities().isBan() ||
                mTopicItem.getAbilities().isClose() || mTopicItem.getAbilities().isOpen() ||
                mTopicItem.getAbilities().isExcellent() || mTopicItem.getAbilities().isUnexcellent()) {
            topicDetailViewHolder.llAdminBox.setVisibility(View.VISIBLE);

            if (mTopicItem.getAbilities().isUpdate()) {
                topicDetailViewHolder.tvEdit.setVisibility(View.VISIBLE);
            }
//            if (mTopicItem.getAbilities().isBan()) {
//                topicDetailViewHolder.tvBan.setVisibility(View.VISIBLE);
//            }
//            if (mTopicItem.getAbilities().isClose() && mTopicItem.getClosedAt() == null) {
//                topicDetailViewHolder.tvClose.setVisibility(View.VISIBLE);
//            }
//            if (mTopicItem.getAbilities().isOpen() && mTopicItem.getClosedAt() != null) {
//                topicDetailViewHolder.tvOpen.setVisibility(View.VISIBLE);
//            }
//            if (mTopicItem.getAbilities().isExcellent() && mTopicItem.getExcellent() == 0) {
//                topicDetailViewHolder.tvDoExcellent.setVisibility(View.VISIBLE);
//            }
//            if (mTopicItem.getAbilities().isUnexcellent() && mTopicItem.getExcellent() > 0) {
//                topicDetailViewHolder.tvUnDoExcellent.setVisibility(View.VISIBLE);
//            }
        }

        if (mTopicItem.getExcellent() > 0) {
            topicDetailViewHolder.iiExcellent.setVisibility(View.VISIBLE);
            topicDetailViewHolder.tvExcellent.setVisibility(View.VISIBLE);
        }

        topicDetailViewHolder.tvUserName.setText(mTopicItem.getUser().getLogin());
        topicDetailViewHolder.tvNodeName.setText(mTopicItem.getNodeName());
        topicDetailViewHolder.tvCreatedDateTime.setText("发布于 " + TimeUtils.getFriendlyTimeSpanByNow(mTopicItem.getCreatedAt(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")));
        topicDetailViewHolder.tvLikeCount.setText(String.valueOf(mTopicItem.getLikesCount()));
        topicDetailViewHolder.tvHitCount.setText(String.valueOf(mTopicItem.getHits()));
        topicDetailViewHolder.tvReplyCount.setText(String.valueOf(mTopicItem.getRepliesCount()));
        Picasso.with(context)
                .load(mTopicItem.getUser().getAvatarUrl())
                .placeholder(R.mipmap.noavatar)
                .into(topicDetailViewHolder.ivUserImage);
        topicDetailViewHolder.mvContentBody.addStyleSheet(MarkDownStyle.getStyle())
                .loadMarkdown("## " + mTopicItem.getTitle() + "\n\n" + mTopicItem.getBody());
    }

}
