package org.mstudio.homeland4android.ui.user_user_list;

import org.mstudio.homeland4android.data.network.model.adjusted.UserListItem;
import org.mstudio.homeland4android.ui.base.BasePresenter;
import org.mstudio.homeland4android.ui.base.BaseView;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public interface UserUserListContract {

    interface View extends BaseView<Presenter> {

        void loadUserList(List<UserListItem> users, boolean reload);

    }

    interface Presenter extends BasePresenter {

        void loadUserList(boolean reload);

    }
}
