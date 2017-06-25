package org.zeropage.apps.zeropage.main.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by gnidoc327 on 2017. 6. 25..
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public static final int MAX_PAGE = 3;
    private ArrayList<WebViewFragment> arrayList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        arrayList = new ArrayList<>();
        arrayList.add(new NotiFragment());
        arrayList.add(new ChangeFragment());
        arrayList.add(new SearchFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public int getCount() {
        return MAX_PAGE;
    }
}
