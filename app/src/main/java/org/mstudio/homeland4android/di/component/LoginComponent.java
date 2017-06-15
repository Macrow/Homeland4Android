package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.LoginModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.login.LoginActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = BasicComponent.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}
