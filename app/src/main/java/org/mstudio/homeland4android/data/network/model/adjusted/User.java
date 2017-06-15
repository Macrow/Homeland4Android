package org.mstudio.homeland4android.data.network.model.adjusted;

import org.mstudio.homeland4android.data.network.model.raw.RawUser;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
public class User {

    public RawUser.UserBean mUser;
    public RawUser.MetaBean mStatus;

    public User(RawUser.UserBean user, RawUser.MetaBean meta) {
        mUser = user;
        mStatus = meta;
    }

}
