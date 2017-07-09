package org.zeropage.apps.zeropage.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.data_singleton.User;
import org.zeropage.apps.zeropage.guide.GuideActivity;
import org.zeropage.apps.zeropage.login.LoginActivity;
import org.zeropage.apps.zeropage.main.MainActivity;
import org.zeropage.apps.zeropage.utility.LocalData;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);

//        AnimatedSvgView svgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);
//        svgView.start();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(LocalData.getFirstVisit(this)) { // 첫방문은 가이드로
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            } else if(LocalData.getUserName(this) != null) { //로그인을 했었다면 메인으로
                User.getInstance().setNickname(LocalData.getUserName(this));
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }

            finish();
        }, 1000);

        //crash report 테스트
//        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
    }
}
