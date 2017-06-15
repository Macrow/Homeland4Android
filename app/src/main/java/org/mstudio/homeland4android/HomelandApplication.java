package org.mstudio.homeland4android;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.LeakCanary;

import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.di.component.DaggerBasicComponent;
import org.mstudio.homeland4android.di.component.BasicComponent;
import org.mstudio.homeland4android.di.module.BasicModule;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public class HomelandApplication extends Application {

    private BasicComponent mBasicComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) { return; }
        LeakCanary.install(this);
        Utils.init(getApplicationContext());
        mBasicComponent = DaggerBasicComponent.builder()
                .basicModule(new BasicModule(new TokenManager(this)))
                .build();
    }

    public BasicComponent getBasicComponent() {
        return mBasicComponent;
    }
}
