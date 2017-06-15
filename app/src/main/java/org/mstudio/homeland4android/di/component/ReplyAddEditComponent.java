package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.ReplyAddEditModule;
import org.mstudio.homeland4android.di.notation.ActivityScope;
import org.mstudio.homeland4android.ui.reply_add_edit.ReplyAddEditActivity;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/10
 * desc   :
 */
@ActivityScope
@Component(modules = ReplyAddEditModule.class, dependencies = BasicComponent.class)
public interface ReplyAddEditComponent {

    void inject(ReplyAddEditActivity replyAddEditActivity);

}
