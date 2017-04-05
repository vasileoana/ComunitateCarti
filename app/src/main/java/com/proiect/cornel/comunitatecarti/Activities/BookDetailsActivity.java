package com.proiect.cornel.comunitatecarti.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.proiect.cornel.comunitatecarti.Classes.Carte;
import com.proiect.cornel.comunitatecarti.Classes.Statics;
import com.proiect.cornel.comunitatecarti.R;

import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {

    private ListView reviews = null;
    private Button btnAddReview = null;
    private Button btnImprumuta = null;
    private EditText etReview = null;
    private TextView titlu = null;
    private TextView categorie = null;
    private TextView autor = null;
    private RatingBar ratingCarte = null;
    public ArrayList<String> listaReview = null;
    private Carte carte;

    protected void setListView() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        listaReview) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        return view;
                    }
                };
        reviews.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Intent intent = getIntent();
        int pozitie = (int) intent.getExtras().get("pozitie");
        titlu = (TextView) findViewById(R.id.idTitluDetails);
        categorie = (TextView) findViewById(R.id.categDetalii);
        reviews = (ListView) findViewById(R.id.lvReviews);
        btnAddReview = (Button) findViewById(R.id.btnAdaugaReview);
        btnImprumuta = (Button) findViewById(R.id.btnImprumutaCarte);
        etReview = (EditText) findViewById(R.id.etReview);
        ratingCarte = (RatingBar) findViewById(R.id.idRatingBar);
        autor = (TextView) findViewById(R.id.tvAutor);

        carte = Statics.listaTotalaCarti.get(pozitie);
        titlu.setText(carte.getDenumire());
        categorie.setText(carte.getCategorie());
        autor.setText(carte.getAutor());
        ratingCarte.setRating((float) carte.getRating());

        listaReview = new ArrayList<>();
        listaReview.add("Util1: Foarte interesanta carte");
        listaReview.add("Util2: A fost foarte placuta");
        listaReview.add("Util3: Nu pot spune ca e cea mai buna carte din aceasta categorie");
        listaReview.add("Util4: Foarte interesanta carte");
        setListView();

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etReview.getText().toString().equals("")) {
                    Toast.makeText(BookDetailsActivity.this, "Scrie mai intai un comentariu", Toast.LENGTH_SHORT).show();
                } else {
                    String review = etReview.getText().toString();
                    listaReview.add(0, Statics.userCurent.getUsername() + ": " + review);
                    setListView();
                    etReview.setText("");
                }
            }
        });

        btnImprumuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookDetailsActivity.this, "Numarul de telefon, respectiv email-ul userului ce detine cartea sunt: " + Statics.getNrTelefonEmail(carte.getIdUtilizator()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
