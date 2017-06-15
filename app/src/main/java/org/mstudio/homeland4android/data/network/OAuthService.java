package org.mstudio.homeland4android.data.network;

import org.mstudio.homeland4android.data.network.model.raw.RawToken;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public interface OAuthService {

    @POST("oauth/token")
    Observable<RawToken> getTokenByAuthCode(@Query("client_id") String client_id,
                                             @Query("client_secret") String client_secret,
                                             @Query("code") String code,
                                             @Query("grant_type") String grant_type,
                                             @Query("redirect_uri") String redirect_uri);

    @POST("oauth/token")
    Observable<RawToken> getTokenByRefreshToken(@Query("refresh_token") String refresh_token,
                                                     @Query("grant_type") String grant_type);

}
