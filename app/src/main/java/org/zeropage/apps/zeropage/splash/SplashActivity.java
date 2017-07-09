package org.zeropage.apps.zeropage.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.crash.FirebaseCrash;
import com.jrummyapps.android.widget.AnimatedSvgView;

import io.fabric.sdk.android.Fabric;
import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.guide.GuideActivity;

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
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
        }, 2*1000);

        //crash report 테스트
//        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
    }
}
