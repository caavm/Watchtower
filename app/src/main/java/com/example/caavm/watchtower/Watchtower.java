package com.example.caavm.watchtower;

import android.app.Application;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.Volley;
/**
 * Created by CAAVM on 03/06/2016.
 */
public class Watchtower extends Application {
    private static final int TIME_OUT =8000;
    private static final int NUM_RETRY =3;
    private static final String TAG = Watchtower.class.getName();
    private RequestQueue requestQueue;
    private static Watchtower instance;
    public static synchronized Watchtower getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance=this;
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Log.d("volley", "inicio");
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void add(Request<T> request){
        request.setTag(TAG);
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, NUM_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(request);
        Log.d("volley", "envio");
    }

    public void cancel(){
        requestQueue.cancelAll(TAG);
    }
}
