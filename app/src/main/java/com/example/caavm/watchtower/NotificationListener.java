package com.example.caavm.watchtower;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.telephony.SmsManager;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.example.caavm.watchtower.Watchtower.*;

public class NotificationListener extends NotificationListenerService {
    Context context;
    String title;
    String text;
    String pack;
    String ticker;
    URLConnection connex;

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }
    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {


        pack = sbn.getPackageName();
        ticker = sbn.getNotification().tickerText.toString();
        Bundle extras = sbn.getNotification().extras;
        title = extras.getString("android.title");
        text = extras.getCharSequence("android.text").toString();

        Log.i("Package",pack);
        Log.i("Ticker",ticker);
        Log.i("Title", title);
        Log.i("Text", text);
        try {
            pack=URLEncoder.encode(pack,"UTF-8");
            ticker=URLEncoder.encode(ticker,"UTF-8");
            title=URLEncoder.encode(title,"UTF-8");
            text=URLEncoder.encode(text,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String link = "http://www.inkadroid.com/usil2016/desarrollo/1/WATCHTOWER_WS.php?title="+ticker+"&content="+pack+title+text;

        Log.i("LINK", link);
        try {
            URL url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        try {
            request.setURI(new URI(link));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override

    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");

    }
}
