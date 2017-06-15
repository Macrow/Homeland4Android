package org.mstudio.homeland4android.ui.user_profile;

import org.mstudio.homeland4android.data.network.model.adjusted.User;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
public interface UserProfileContract {

    interface View extends BaseView<Presenter> {

        void loadProfile(User user);

        void loadActionView(boolean isBlocked, boolean isFollowed);

        void updateUserFollowers(String number);

    }

    interface Presenter extends BasePresenter {

        void loadProfile();

        String getUserLoginName();

        void updateBlockUserStatus();

        void updateFollowingUserStatus();

    }
}
