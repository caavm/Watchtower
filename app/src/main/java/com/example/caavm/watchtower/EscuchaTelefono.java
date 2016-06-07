package com.example.caavm.watchtower;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by CAAVM on 27/05/2016.
 */
public class EscuchaTelefono extends PhoneStateListener {
    Context context;
    private String numerophone;
    int cont=0;

    public EscuchaTelefono(Context context, String numerophone) {
        this.context = context;
        this.numerophone = numerophone;
    }

    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                if(cont==0) {
                    Toast.makeText(this.context, "LLAMADA ENTRANTE", Toast.LENGTH_LONG).show();
                    String phoneNumber = numerophone;
                    envioBotonSos(phoneNumber);
                    cont++;
                }
                break;
        }
    }

    public void envioBotonSos(String phoneNumber){
        SmsManager smsx=SmsManager.getDefault();
        if(phoneNumber!=null) {
            smsx.sendTextMessage("+51978000376", null, "NUMERO: " + phoneNumber, null, null);
        }
    }
}
