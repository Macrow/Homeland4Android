package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.TopicAddEditModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.topic_add_edit.TopicAddEditActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/9
 * desc   :
 */
@ActivityScope
@Component(modules = TopicAddEditModule.class, dependencies = BasicComponent.class)
public interface TopicAddEditComponent {

    void inject(TopicAddEditActivity topicAddEditActivity);

}
