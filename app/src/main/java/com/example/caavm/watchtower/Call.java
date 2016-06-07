package com.example.caavm.watchtower;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by CAAVM on 27/05/2016.
 */
public class Call extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String numero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        EscuchaTelefono phoneListener = new EscuchaTelefono(context, numero);
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
