package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.data.AuthorizedDataManager;
import org.mstudio.homeland4android.data.NoAuthorizedDataManager;
import org.mstudio.homeland4android.data.OAuthDataManager;
import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.di.module.BasicModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
@Singleton
@Component(modules = BasicModule.class)
public interface BasicComponent {

    TokenManager provideTokenManager();

    NoAuthorizedDataManager provideDataManager();

    OAuthDataManager provideOAuthDataManager();

    AuthorizedDataManager provideAuthorizedDataManager();

}
