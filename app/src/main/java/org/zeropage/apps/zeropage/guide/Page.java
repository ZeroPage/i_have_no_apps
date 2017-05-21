package org.zeropage.apps.zeropage.guide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zeropage.apps.zeropage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page extends Fragment {
    int page;

    public Page() {

    }
    public void setPage(int page){
        this.page = page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(page, container, false);
    }

}
