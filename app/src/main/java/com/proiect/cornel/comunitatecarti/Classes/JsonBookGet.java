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

/**
 * Created by corne on 07.12.2016.
 */

public class JsonBookGet  extends AsyncTask<String, Void, Void>{

    @Override
    protected Void doInBackground(String... strings) {
        String link = strings[0];
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream input = con.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader in = new BufferedReader(reader);
            String line = in.readLine();
            JSONArray jsonArray = new JSONArray(line);
            JSONObject jObject = null;
            Carte carte = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(jsonArray.length());
                jObject = jsonArray.getJSONObject(i);
                int idCarte = jObject.getInt("id");
                String denumire = jObject.getString("denumire");
                String autor = jObject.getString("autor");
                String categorie = jObject.getString("categorie");
                double rating = jObject.getDouble("rating");
                int idUtilizator = jObject.getInt("idUtilizator");
                String dispString = jObject.getString("disponibilitate");
                int disp = 1;
                if (dispString.equals("")) {
                    disp = 0;
                }
                boolean disponibilitate = false;
                if (disp == 1) {
                    disponibilitate = true;
                }
                carte = new Carte(idCarte, denumire, autor, categorie, rating, disponibilitate, idUtilizator);
                Statics.listaTotalaCarti.add(carte);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
