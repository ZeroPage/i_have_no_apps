package org.zeropage.apps.zeropage.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import org.zeropage.apps.zeropage.R;

/**
 * Created by user on 2017-05-21.
 */

public class GuideAdapter extends FragmentPagerAdapter {
    static final int GUIDE_PAGES = 7;
    ArrayList<Fragment> arrayList = new ArrayList<>();

    public GuideAdapter(FragmentManager fm) {
        super(fm);
        Page first = new Page();
        first.setPage(R.layout.fragment_page);
        first.setImg(R.drawable.zp_logo);
        arrayList.add(first);
        Page second = new Page();
        second.setPage(R.layout.fragment_page2);
        second.setImg(R.drawable.menu);
        arrayList.add(second);
        Page third = new Page();
        third.setPage(R.layout.fragment_page3);
        third.setImg(R.drawable.slackbot);
        arrayList.add(third);
        Page fourth = new Page();
        fourth.setPage(R.layout.fragment_page4);
        fourth.setImg(R.drawable.get_otp);
        arrayList.add(fourth);
        Page fifth = new Page();
        fifth.setPage(R.layout.fragment_page5);
        fifth.setImg(R.drawable.otp_enter);
        arrayList.add(fifth);
        Page sixth = new Page();
        sixth.setPage(R.layout.fragment_page6);
        sixth.setImg(R.drawable.login_error);
        arrayList.add(sixth);
        Page seventh = new Page();
        seventh.setPage(R.layout.fragment_last_page);
        arrayList.add(seventh);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return GUIDE_PAGES;
    }


}
