package org.mstudio.homeland4android.data.network.model.adjusted;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.TimeUtils;
import com.squareup.picasso.Picasso;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.raw.RawTopics;
import org.mstudio.homeland4android.ui.topics.TopicViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.utils.DrawableUtils;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class TopicListItem extends AbstractFlexibleItem {

    public RawTopics.TopicsBean mTopicItem;

    public TopicListItem(RawTopics.TopicsBean topicsBean) {
        mTopicItem = topicsBean;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_topic;
    }

    @Override
    public TopicViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new TopicViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {
        TopicViewHolder topicViewHolder = (TopicViewHolder) holder;
        Context context = topicViewHolder.tvTopicTitle.getContext();
        topicViewHolder.tvNodeName.setText(mTopicItem.getNodeName());
        topicViewHolder.tvUserName.setText(mTopicItem.getUser().getLogin());

        if (mTopicItem.getRepliesCount() > 0) {
            topicViewHolder.tvCreatedDateTime.setText(
                    "最后由 " + mTopicItem.getLastReplyUserLogin() + " 回复于"
                             + TimeUtils.getFriendlyTimeSpanByNow(mTopicItem.getRepliedAt(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"))
            );
        } else {
            topicViewHolder.tvCreatedDateTime.setText(
                    "发布于" + TimeUtils.getFriendlyTimeSpanByNow(mTopicItem.getCreatedAt(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"))
            );
        }

        if (mTopicItem.getExcellent() > 0) {
            topicViewHolder.tvTopicTitle.setText("\uD83D\uDD25 " + mTopicItem.getTitle());
        } else {
            topicViewHolder.tvTopicTitle.setText(mTopicItem.getTitle());
        }

        topicViewHolder.tvHitCount.setText(String.valueOf(mTopicItem.getHits()));
        topicViewHolder.tvReplyCount.setText(String.valueOf(mTopicItem.getRepliesCount()));
        Picasso.with(context)
                .load(mTopicItem.getUser().getAvatarUrl())
                .placeholder(R.mipmap.noavatar)
                .into(topicViewHolder.ivUserImage);

        Drawable drawable = DrawableUtils.getSelectableBackgroundCompat(
                Color.WHITE, Color.LTGRAY, Color.LTGRAY);
        DrawableUtils.setBackgroundCompat(holder.itemView, drawable);
    }
}
