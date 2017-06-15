package org.mstudio.homeland4android.data;

import org.mstudio.homeland4android.data.network.OAuthService;
import org.mstudio.homeland4android.data.network.model.raw.RawToken;
import org.mstudio.homeland4android.util.AppConstant;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class OAuthDataManager {

    private OAuthService mOAuthService;

    @Inject
    public OAuthDataManager(OAuthService oAuthService) {
        mOAuthService = oAuthService;
    }

    public Observable<RawToken> LoginByAuthCode(String code) {
        return mOAuthService.getTokenByAuthCode(
                AppConstant.CLIENT_ID,
                AppConstant.CLIENT_SECRET,
                code,
                AppConstant.GRANT_TYPE_AUTH_CODE,
                AppConstant.CALLBACK_URI);
    }

}
