package org.zeropage.apps.zeropage.guide;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.melnykov.fab.FloatingActionButton;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page extends Fragment {
    int page, img;
    FloatingActionButton fab;
    public Page() {

    }
    public void setPage(int page) {
        this.page = page;
    }
    public void setImg(int img){
        this.img = img;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(page, container, false);
        ImageView iv = (ImageView)view.findViewById(R.id.ImageView);
        Glide.with(this).load(img).into(iv);
        return view;
    }

}
