package org.zeropage.apps.zeropage.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMInitializationService extends FirebaseInstanceIdService {
    private static final String TAG = "FCMInitService";

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "FCM Device Token : " + fcmToken);
    }
}
