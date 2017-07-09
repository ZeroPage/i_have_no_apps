package org.zeropage.apps.zeropage.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.login.LoginActivity;

/**
 * Created by gnidoc on 2017-03-29.
 */
public class GuideActivity extends AppCompatActivity {
    ViewPager viewPager;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        GuideAdapter guideAdapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setAdapter(guideAdapter);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               Intent intent = new Intent(GuideActivity.this , LoginActivity.class);
               startActivity(intent);
            }
        });
    }
}