package org.mstudio.homeland4android.ui.profile;

import org.mstudio.homeland4android.data.network.model.adjusted.User;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void loadProfile(User user);

        void updateUnreadNotificationCount(int count);

    }

    interface Presenter extends BasePresenter {

        void loadProfile();

        void Logout();

        String getProfileLoginName();

    }
}
