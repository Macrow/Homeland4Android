package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.NotificationsModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.notifications.NotificationsActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/13
 * desc   :
 */
@ActivityScope
@Component(modules = NotificationsModule.class, dependencies = BasicComponent.class)
public interface NotificationsComponent {

    void inject(NotificationsActivity notificationsActivity);

}
