package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.UserTopicListModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.user_topic_list.UserTopicListActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/06
 * desc   :
 */
@ActivityScope
@Component(modules = UserTopicListModule.class, dependencies = BasicComponent.class)
public interface UserTopicListComponent {

    void inject(UserTopicListActivity userTopicListActivity);

}
