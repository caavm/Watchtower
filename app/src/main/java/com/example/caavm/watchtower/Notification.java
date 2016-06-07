package com.example.caavm.watchtower;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by CAAVM on 03/06/2016.
 */
public class Notification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationListener notificationListener = new NotificationListener();
    }
}
