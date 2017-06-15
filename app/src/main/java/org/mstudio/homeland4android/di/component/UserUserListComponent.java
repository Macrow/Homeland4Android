package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.UserUserListModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.user_user_list.UserUserListActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
@ActivityScope
@Component(modules = UserUserListModule.class, dependencies = BasicComponent.class)
public interface UserUserListComponent {

    void inject(UserUserListActivity userUserListActivity);

}
