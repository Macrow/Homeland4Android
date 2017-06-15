package org.mstudio.homeland4android.ui.notifications;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.adjusted.NotificationListItem;
import org.mstudio.homeland4android.ui.topic_detail.TopicDetailActivity;
import org.mstudio.homeland4android.util.FastClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

import static org.mstudio.homeland4android.ui.topic_detail.TopicDetailActivity.EXTRA_TOPIC_ID;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/13
 * desc   :
 */
public class NotificationViewHolder extends FlexibleViewHolder {

    public @BindView(R.id.tvUserName) TextView tvUserName;
    public @BindView(R.id.tvCreatedDateTime) TextView tvCreatedDateTime;
    public @BindView(R.id.tvTopicTitle) TextView tvTopicTitle;
    public @BindView(R.id.tvNotificationInfo) TextView tvNotificationInfo;
    public @BindView(R.id.ivUserImage) ImageView ivUserImage;

    public NotificationViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (!FastClickUtil.isFastClick()) {
            NotificationListItem notification = (NotificationListItem) mAdapter.getItem(getAdapterPosition());
            if (notification.mNotification.getTopic() != null) {
                Intent intent = new Intent(view.getContext(), TopicDetailActivity.class);
                intent.putExtra(EXTRA_TOPIC_ID, notification.mNotification.getTopic().getId());
                view.getContext().startActivity(intent);
            } else {
                // TODO loadUserActivity
            }
        }
    }

}