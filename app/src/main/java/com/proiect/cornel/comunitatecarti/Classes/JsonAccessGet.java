package com.proiect.cornel.comunitatecarti.Classes;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by corne on 28.11.2016.
 */

public class JsonAccessGet extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... args) {
        String linkUsers = args[0];
        try {
            URL url = new URL(linkUsers);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream input = con.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader in = new BufferedReader(reader);
            String line = in.readLine();
            JSONArray jsonArray = new JSONArray(line);
            JSONObject jObject = null;
            User user = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jObject = jsonArray.getJSONObject(i);
                int id = (int) jObject.get("id");
                String nume = jObject.get("Nume").toString();
                String prenume = jObject.get("Prenume").toString();
                String username = jObject.get("Utilizator").toString();
                String parola = jObject.get("Parola").toString();
                String email = jObject.get("Email").toString();
                String telefon = jObject.get("Telefon").toString();
                String sex = jObject.get("Sex").toString();
                String dataS = jObject.get("DataNasterii").toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date data = format.parse(dataS);
                user = new User(id, nume, prenume, username, parola, email, telefon, sex, data);
                Statics.useri.add(user);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
            Statics.userCurent = null;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
