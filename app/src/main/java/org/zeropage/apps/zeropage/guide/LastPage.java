package org.zeropage.apps.zeropage.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.login.LoginActivity;

/**
 * Created by user on 2017-06-25.
 */

public class LastPage extends Fragment {
    public LastPage(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.fragment_last_page, container, false);
        Button btn = (Button)view.findViewById(R.id.go_to_login_page);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
