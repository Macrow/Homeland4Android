package org.mstudio.homeland4android.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

import static org.mstudio.homeland4android.ui.topic_add_edit.TopicAddEditActivity.EXTRA_CAMERA_IMAGE_PATH;
import static org.mstudio.homeland4android.util.AppConstant.ALBUM_REQUEST_CODE;
import static org.mstudio.homeland4android.util.AppConstant.CAMERA_REQUEST_CODE;
import static org.mstudio.homeland4android.util.AppConstant.SAVED_IMAGE_DIR_PATH;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/15
 * desc   :
 */
public class ImageGetterUtil {

    public static String openCamera(Activity activity) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            String cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg";
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            String out_file_path = SAVED_IMAGE_DIR_PATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Uri uri = Uri.fromFile(new File(cameraPath));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra(cameraPath, EXTRA_CAMERA_IMAGE_PATH);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            activity.startActivityForResult(intent, CAMERA_REQUEST_CODE);
            return cameraPath;
        } else {
            Toast.makeText(activity, "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static void openGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, ALBUM_REQUEST_CODE);
    }

    public static String getAbsolutePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static String getFormatUrl(String url) {
        return "\n![](" + url + ")\n";
    }
}
