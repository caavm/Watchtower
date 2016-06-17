package com.example.caavm.watchtower;

import android.app.Application;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
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
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();
        Log.d("volley", "inicio");
    }

    public RequestQueue getRequestQueue() {
        Log.d("volley", "requestQueue");
        return requestQueue;
    }

    public <String> void add(Request<String> request){
        request.setTag(TAG);
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, NUM_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    public void cancel(){
        requestQueue.cancelAll(TAG);
    }
}
