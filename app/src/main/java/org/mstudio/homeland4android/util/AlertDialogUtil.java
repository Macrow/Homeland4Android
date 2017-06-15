package org.mstudio.homeland4android.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import org.mstudio.homeland4android.ui.base.AlertDialogCaller;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/12
 * desc   :
 */
public class AlertDialogUtil {

    public static AlertDialog buildDialog(Context context, final AlertDialogCaller caller) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("请确认您的操作");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                caller.OnConfirmClick();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        return dialog;
    }

}
