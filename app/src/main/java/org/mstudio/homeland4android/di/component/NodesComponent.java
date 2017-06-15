package org.mstudio.homeland4android.di.component;

import org.mstudio.homeland4android.di.module.NodesModule;
import org.mstudio.homeland4android.di.notation.FragmentScope;
import org.mstudio.homeland4android.ui.nodes.NodesDialogFragment;

import dagger.Component;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
@FragmentScope
@Component(modules = NodesModule.class, dependencies = BasicComponent.class)
public interface NodesComponent {

    void inject(NodesDialogFragment nodesDialogFragment);

}
