package com.proiect.cornel.comunitatecarti.Classes;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by i332191 on 05/12/2016.
 */

public class JsonAccessPost extends AsyncTask<URL, Void, Void> {

    @Override
    protected Void doInBackground(URL... urls) {
        URL url = urls[0];

        try {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            InputStream input = con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
