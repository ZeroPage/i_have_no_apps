package org.zeropage.apps.zeropage.log;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by gnidoc327 on 2017. 5. 8..
 * 배포시엔 flag false 로 내보낼것(최소한의 보안사항)
 * 이걸 Flavor로 관리하자
 */

public class ZpLog {
    private static final boolean FLAG = true;

    /*

     */
    public static void debug(@NonNull String tag, String msg){
        if(FLAG){
            Log.d(tag, msg);
        }
    }

    public static void info(@NonNull String tag, String msg){
        if(FLAG){
            Log.i(tag, msg);
        }
    }

    public static void err(@NonNull String tag, String msg){
        if(FLAG){
            Log.e(tag, msg);
        }
    }

    public static void verbose(@NonNull String tag, String msg){
        if(FLAG){
            Log.v(tag, msg);
        }
    }

    public static void warnning(@NonNull String tag, String msg){
        if(FLAG){
            Log.w(tag, msg);
        }
    }
}
