package org.zeropage.apps.zeropage.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import org.zeropage.apps.zeropage.R;

/**
 * Created by user on 2017-05-21.
 */

public class GuideAdapter extends FragmentPagerAdapter {
    static final int GUIDE_PAGES = 3;
    ArrayList<Fragment> arrayList = new ArrayList<>();

    public GuideAdapter(FragmentManager fm) {
        super(fm);
        Page first = new Page();
        first.setPage(R.layout.fragment_page);
        arrayList.add(first);
        Page second = new Page();
        second.setPage(R.layout.fragment_page2);
        arrayList.add(second);
        Page third = new Page();
        third.setPage(R.layout.fragment_page3);
        arrayList.add(third);
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
