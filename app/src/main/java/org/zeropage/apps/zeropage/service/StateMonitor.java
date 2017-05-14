package org.zeropage.apps.zeropage.service;

/**
 * Created by kimhs on 2017-05-14.
 */

public class StateMonitor {
    private static final StateMonitor ourInstance = new StateMonitor();
    private NetStates networkState;
    //나중에 필요하다면....
    //private GpsStates gpsState;
    //private ServiceStates serviceStates;

    public enum NetStates {
        Connected,
        Connecting,
        Disconnect
    }

    public static StateMonitor getInstance() {
        return ourInstance;
    }

    private StateMonitor() {
        networkState = NetStates.Disconnect;
    }

    public boolean NetworkIsEnable(){
        return false;
    }

    public void setNetworkState(NetStates changedState){
        networkState = changedState;
    }

    public NetStates getNetworkState(){
        return networkState;
    }
}
