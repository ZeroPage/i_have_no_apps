package org.zeropage.apps.zeropage.log;

import android.support.annotation.NonNull;
import android.util.Log;

import org.zeropage.apps.zeropage.BuildConfig;

/**
 * Created by gnidoc327 on 2017. 5. 8..
 * 배포시엔 flag false 로 내보낼것(최소한의 보안사항)
 * 이걸 Flavor로 관리하자
 *
 */

public class ZpLog {
    private static final String KEYWORD = "Debug";
    /*

     */
    public static void d(@NonNull String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void i(@NonNull String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.i(tag, msg);
        }

        Log.i("123", BuildConfig.BUILD_TYPE);
    }

    public static void e(@NonNull String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.e(tag, msg);
        }
    }

    public static void v(@NonNull String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.v(tag, msg);
        }
    }

    public static void w(@NonNull String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.w(tag, msg);
        }
    }
}
