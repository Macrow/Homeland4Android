package org.mstudio.homeland4android.util;

import android.content.Intent;

import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.ui.base.BaseView;
import org.mstudio.homeland4android.ui.login.LoginActivity;

/**
 * Created by macrow on 2017/6/9.
 */

public class LoginStateUtil {

    public static boolean checkLoginState(TokenManager tokenManager, BaseView baseView) {
        if (!tokenManager.haveTokens()) {
            Intent intent = new Intent(tokenManager.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            tokenManager.getContext().startActivity(intent);
            tokenManager.Logout();
            baseView.finishActivity();
            return false;
        } else {
            return true;
        }
    }

}
