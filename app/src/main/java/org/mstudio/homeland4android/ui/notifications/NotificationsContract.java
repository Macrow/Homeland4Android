package org.mstudio.homeland4android.ui.notifications;

import org.mstudio.homeland4android.data.network.model.adjusted.NotificationListItem;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/13
 * desc   :
 */
public interface NotificationsContract {

    interface View extends BaseView<Presenter> {

        void loadNotificationList(List<NotificationListItem> notifications, boolean reload);

        void clearAllNotifications();

    }

    interface Presenter extends BasePresenter {

        void loadNotificationList(boolean reload);

    }
}
