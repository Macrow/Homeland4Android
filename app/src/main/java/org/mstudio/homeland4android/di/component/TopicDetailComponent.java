package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.TopicDetailModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.topic_detail.TopicDetailActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
@ActivityScope
@Component(modules = TopicDetailModule.class, dependencies = BasicComponent.class)
public interface TopicDetailComponent {

    void inject(TopicDetailActivity topicDetailActivity);

}
