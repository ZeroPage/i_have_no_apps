package org.zeropage.apps.zeropage.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by kimhs on 2017-05-14.
 */

public class StateManager extends Service{
    private ConnectivityManager connectivityManager;

    BroadcastReceiver networkBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();for(NetworkInfo networkInfo : networkInfos){
                    if(networkInfo.isConnected()){
                        StateMonitor.getInstance().setNetworkState(StateMonitor.NetStates.Connected);
                    }

                    StateMonitor.getInstance().setNetworkState(StateMonitor.NetStates.Disconnect);
                }
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initManager();
        registerAllReceiver();

        return super.onStartCommand(intent, flags, startId);
    }

    public void registerAllReceiver(){
        registerReceiver(networkBroadcastReceiver, getNetworkIntentFilter());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    public void initManager(){
        connectivityManager = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public IntentFilter getNetworkIntentFilter(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        return intentFilter;
    }
}
