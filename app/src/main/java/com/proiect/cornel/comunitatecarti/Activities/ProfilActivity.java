package com.proiect.cornel.comunitatecarti.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.proiect.cornel.comunitatecarti.Classes.Statics;
import com.proiect.cornel.comunitatecarti.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfilActivity extends AppCompatActivity {

    TextView nume = null;
    TextView prenume = null;
    TextView email = null;
    TextView telefon = null;
    TextView uname = null;
    TextView sex = null;
    TextView varsta = null;
    TextView listaMea = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        nume = (TextView) findViewById(R.id.numeProfil);
        prenume = (TextView) findViewById(R.id.prenumeProfil);
        email = (TextView) findViewById(R.id.emailProfil);
        telefon = (TextView) findViewById(R.id.telefonProfil);
        uname = (TextView) findViewById(R.id.utilizatorProfil);
        sex = (TextView) findViewById(R.id.sexProfil);
        varsta = (TextView) findViewById(R.id.varstaProfil);
        listaMea = (TextView) findViewById(R.id.tvListaCarti);

        if(Statics.userCurent != null) {
            nume.setText(Statics.userCurent.getNume());
            prenume.setText(Statics.userCurent.getPrenume());
            email.setText(Statics.userCurent.getEmail());
            telefon.setText(Statics
                    .userCurent.getTelefon());
            uname.setText(Statics.userCurent.getUsername());
            sex.setText(Statics.userCurent.getSex());
        }else{
            nume.setText("null");
            prenume.setText("null");
            email.setText("null");
            telefon.setText("null");
            uname.setText("null");
        }
    }

    /**
     * Created by i332191 on 05/12/2016.
     */

    public static class JsonAccessPost extends AsyncTask<URL, Void, Void> {

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
}
