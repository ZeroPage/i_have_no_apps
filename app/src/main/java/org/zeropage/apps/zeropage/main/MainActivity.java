package org.zeropage.apps.zeropage.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.service.StateManager;
import org.zeropage.apps.zeropage.service.StateMonitor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
