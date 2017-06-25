package org.zeropage.apps.zeropage.utility;


import android.app.Activity;
import android.widget.Toast;

/**
 * Created by hyom on 2017-05-20.
 */

public class BackPressCloseHandler {

    private float backKeyPressedTime = 0;
    private Activity activity;

    public BackPressCloseHandler(Activity context){
        this.activity = context;
    }

    public void onBackPressed(){
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){ // 2초가 적당하다니까
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(activity, "한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT).show();
            return ;
        }
        else if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finish();
        }
    }
}