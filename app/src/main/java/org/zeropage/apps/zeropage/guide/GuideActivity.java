package org.zeropage.apps.zeropage.guide;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.zeropage.apps.zeropage.R;

/**
 * Created by gnidoc on 2017-03-29.
 */
public class GuideActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        GuideAdapter guideAdapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setAdapter(guideAdapter);
    }
}