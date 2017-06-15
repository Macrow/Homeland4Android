package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.UserProfileModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.user_profile.UserProfileActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
@ActivityScope
@Component(modules = UserProfileModule.class, dependencies = BasicComponent.class)
public interface UserProfileComponent {

    void inject(UserProfileActivity userProfileActivity);

}
