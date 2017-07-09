package org.zeropage.apps.zeropage.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public final class LocalData {
    private static final String TAG = LocalData.class.getSimpleName();
    private static final String USERNAME = "username";
    private static final String FIRST_VISIT = "first_visit";

    public static void putUserName(@NonNull Context context, @NonNull String userName) {
        Log.i(TAG, "Save to Preferences. name = " + userName);

        getPreferences(context).edit()
                .putString(USERNAME, userName)
                .apply();
    }

    public static void putFirstVisit(@NonNull Context context, @NonNull boolean isVisited) {
        Log.i(TAG, "Save to Preferences. name = " + isVisited);

        getPreferences(context).edit()
                .putBoolean(FIRST_VISIT, isVisited)
                .apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    @Nullable
    public static String getUserName(@NonNull Context context) {
        return getPreferences(context).getString(USERNAME, null);
    }

    @Nullable
    public static Boolean getFirstVisit(@NonNull Context context){
        return getPreferences(context).getBoolean(FIRST_VISIT, true);
    }
}
