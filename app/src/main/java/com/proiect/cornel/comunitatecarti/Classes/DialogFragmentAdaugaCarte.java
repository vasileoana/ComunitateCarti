package com.proiect.cornel.comunitatecarti.Classes;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.proiect.cornel.comunitatecarti.Activities.ProfilActivity;
import com.proiect.cornel.comunitatecarti.R;

import java.net.MalformedURLException;
import java.net.URL;


public class DialogFragmentAdaugaCarte extends DialogFragment {
    Button btnAdauga = null;

    EditText etDenumireCarte = null;
    EditText etAutorCarte = null;
    Spinner spinnerCategorieCarte = null;

    RatingBar rb = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_adauga_carte, container, false);
        getDialog().setTitle("Adauga o carte");

        rb = (RatingBar) rootView.findViewById(R.id.ratingBarCarte);

        etDenumireCarte = (EditText) rootView.findViewById(R.id.etDenumireCarte);
        etAutorCarte = (EditText) rootView.findViewById(R.id.etAutorCarte);
        spinnerCategorieCarte = (Spinner) rootView.findViewById(R.id.spinnerCategorieCarte);
        spinnerCategorieCarte.setDropDownVerticalOffset(50);
        btnAdauga = (Button) rootView.findViewById(R.id.btnAdauga);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String denumireCarte = etDenumireCarte.getText().toString();
                int eroare = 0;
                if (denumireCarte.equals("")) {
                    etDenumireCarte.setError("Introduceti denumirea unei carti");
                    eroare = 1;
                }
                String autorCarte = etAutorCarte.getText().toString();
                if (autorCarte.equals("")) {
                    etAutorCarte.setError("Introduceti autorul cartii");
                    eroare = 1;
                }
                String categorieCarte = spinnerCategorieCarte.getSelectedItem().toString();

                String ratingCarte = Float.toString(rb.getRating());
                if (Statics.userCurent == null || Statics.userCurent.getUsername().equals("#321")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Nu poti adauga carti daca nu esti logat", Toast.LENGTH_SHORT).show();
                    dismiss();
                } else if (eroare == 0) {
                    String link = "https://homeworkspace-cornelsora.c9users.io/Carti/" +
                            denumireCarte.replaceAll(" ", "%20") + "/" + autorCarte.replaceAll(" ", "%20") + "/" +
                            categorieCarte.replaceAll(" ", "%20") + "/" +
                            ratingCarte + "/" + Statics.userCurent.getId();

                    Carte carte = new Carte(denumireCarte, autorCarte, categorieCarte, Double.parseDouble(ratingCarte),
                            true, Statics.userCurent.getId());
                    Statics.listaMeaCarti.add(carte);
                    Statics.listaTotalaCarti.add(carte);

                    ProfilActivity.JsonAccessPost jsonPost = new ProfilActivity.JsonAccessPost();
                    URL url = null;
                    try {
                        url = new URL(link);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    jsonPost.execute(url);
                    Toast.makeText(getActivity().getApplicationContext(), "Carte inserata cu succes!", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        return rootView;
    }

}
