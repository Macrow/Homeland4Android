package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.TopicsModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.topics.TopicsActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
@ActivityScope
@Component(modules = TopicsModule.class, dependencies = BasicComponent.class)
public interface TopicsComponent {

    void inject(TopicsActivity topicsActivity);

}
