package com.dee.sean.turingrobot;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dixin on 16/3/9.
 */
public class HttpData extends AsyncTask<String, Void, String> {

    private HttpClient mHttpClient;
    private HttpGet mHttpGet;
    private String url;
    private HttpResponse mHttpResponse;
    private HttpEntity mHttpEntity;
    private InputStream in;
    private HttpGetDataListener listener;

    public HttpData(String url, HttpGetDataListener listener) {
        this.url = url;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        mHttpClient = new DefaultHttpClient();
        mHttpGet = new HttpGet(url);
        try {
            mHttpResponse = mHttpClient.execute(mHttpGet);
            mHttpEntity = mHttpResponse.getEntity();

            in = mHttpEntity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        listener.getDataUrl(s);
        super.onPostExecute(s);
    }
}
