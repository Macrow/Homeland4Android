package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.ProfileModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.profile.ProfileActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
@ActivityScope
@Component(modules = ProfileModule.class, dependencies = BasicComponent.class)
public interface ProfileComponent {

    void inject(ProfileActivity profileActivity);

}
