package com.proiect.cornel.comunitatecarti.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.proiect.cornel.comunitatecarti.R;

public class MeniuPrincipalActivity extends AppCompatActivity {

    Button btnProfil = null;
    Button btnCarti = null;
    Button btnAdaugaCarte = null;
    Button btnLogout = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu);
        //Toast.makeText(this,Statics.userCurent.getDataNasterii().toString(),Toast.LENGTH_SHORT).show();

        btnProfil = (Button) findViewById(R.id.idProfilMeniu);
        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ProfilActivity.class);
                startActivity(i);
            }
        });

        btnCarti = (Button) findViewById(R.id.idCartiMeniu);
        btnCarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MeniuCartiActivity.class);
                startActivity(i);
            }
        });

        btnAdaugaCarte = (Button) findViewById(R.id.idAdaugaCarte);
        btnAdaugaCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddBookActivity.class);
                startActivity(i);
            }
        });

        btnLogout = (Button) findViewById(R.id.idLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });


    }

    public void btnStatisticiClick(View view){
        PieGraph pieGraph = new PieGraph();
        Intent inlineIntent = pieGraph.getIntent(this);
        startActivity(inlineIntent);
    }

    public void btnStatisticiCanvas(View view) {
        Intent intent = new Intent(getApplicationContext(), PieChartActivity.class);
        startActivity(intent);
    }

}
