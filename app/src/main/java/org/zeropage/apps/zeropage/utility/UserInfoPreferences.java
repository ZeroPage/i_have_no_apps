package org.zeropage.apps.zeropage.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public final class UserInfoPreferences {
    private static final String TAG = UserInfoPreferences.class.getSimpleName();
    private static final String KEY_NAME = "name";

    public static void putUserName(@NonNull Context context, @NonNull String userName) {
        Log.i(TAG, "Save to Preferences. name = " + userName);

        getPreferences(context).edit()
                .putString(KEY_NAME, userName)
                .apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    @Nullable
    public static String getUserName(@NonNull Context context) {
        return getPreferences(context).getString(KEY_NAME, null);
    }
}
