package org.mstudio.homeland4android.util;

import android.os.Environment;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public class AppConstant {

    public static final String APP_NAME = "Homeland4A";
    public static final String GITHUB_LINK = "https://github.com/Macrow/Homeland4Android";

    public static final String BASE_URL = "http://ruby-china.org/";
    public static final String API_BASE_URL = BASE_URL + "api/v3/";
    public static final String OAUTH_AUTH_URL = "http://ruby-china.org/oauth/authorize";

    public static final String CLIENT_ID = "6e8ccd23";
    public static final String CLIENT_SECRET = "aa73ce33974c4ed097e908258df98082c22b4494d60329cfda091c20b22d4ddc";
    public static final String CALLBACK_URI = "homeland4android://oauth";
    public static final String GRANT_TYPE_AUTH_CODE = "authorization_code";
    public static final String OAUTH_LOGIN_URI = OAUTH_AUTH_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + CALLBACK_URI + "&response_type=code";

    public static final int PAGE_LIMIT = 20;
    public final static int ALBUM_REQUEST_CODE = 1;
    public final static int CAMERA_REQUEST_CODE = 2;
    public static String SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().getPath() + "/" + APP_NAME + "/camera/";

}
