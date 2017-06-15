package org.mstudio.homeland4android.data.network.model.adjusted;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.TimeUtils;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.data.network.model.raw.RawNotifications;
import org.mstudio.homeland4android.ui.notifications.NotificationViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.utils.DrawableUtils;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
public class NotificationListItem extends AbstractFlexibleItem {

    public RawNotifications.NotificationsBean mNotification;

    public NotificationListItem(RawNotifications.NotificationsBean notificationsBean) {
        mNotification = notificationsBean;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_notification;
    }

    @Override
    public NotificationViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new NotificationViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {
        NotificationViewHolder notificationViewHolder = (NotificationViewHolder) holder;
        Context context = notificationViewHolder.itemView.getContext();

        if (mNotification.isRead()) {
            notificationViewHolder.tvUserName.setText(mNotification.getActor().getLogin());
        } else {
            notificationViewHolder.tvUserName.setText("**未读**  " + mNotification.getActor().getLogin());
        }
        notificationViewHolder.tvCreatedDateTime.setText("于" +
                TimeUtils.getFriendlyTimeSpanByNow(mNotification.getCreated_at(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")));

        String info;

        switch (mNotification.getType().toLowerCase()) {
            case "topic":
                info = "创建了上述话题";
                break;
            case "topicreply":
                info = "回复了上述话题";
                break;
            case "follow":
                info = "开始关注你了";
                break;
            case "mention":
                info = "在上述话题中提及到了你";
                break;
            case "nodechanged":
                info = "你的话题被移动了节点到" + mNotification.getTopic().getNode_name();
                break;
            default:
                info = "";
                break;
        }
        notificationViewHolder.tvNotificationInfo.setText(info);

        if (mNotification.getTopic() != null) {
            notificationViewHolder.tvTopicTitle.setText(mNotification.getTopic().getTitle());
        }

        Picasso.with(context)
                .load(mNotification.getActor().getAvatar_url())
                .placeholder(R.mipmap.noavatar)
                .into(notificationViewHolder.ivUserImage);

        Drawable drawable = DrawableUtils.getSelectableBackgroundCompat(
                Color.WHITE, Color.LTGRAY, Color.LTGRAY);
        DrawableUtils.setBackgroundCompat(holder.itemView, drawable);
    }
}
