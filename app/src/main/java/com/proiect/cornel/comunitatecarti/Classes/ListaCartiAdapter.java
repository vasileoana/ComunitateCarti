package com.proiect.cornel.comunitatecarti.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.proiect.cornel.comunitatecarti.Classes.Carte;
import com.proiect.cornel.comunitatecarti.R;

import java.util.ArrayList;
import java.util.List;

public class ListaCartiAdapter extends ArrayAdapter<Carte> {

    List<Carte> listaCarti = new ArrayList<>();

    public ListaCartiAdapter(Context context, int resource, List<Carte> listaCarti) {
        super(context, resource, listaCarti);
        this.listaCarti = listaCarti;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.lista_carti_custom, null);
        TextView titluTv = (TextView) view.findViewById(R.id.tvTitluCarte);
        RatingBar rb = (RatingBar) view.findViewById(R.id.ratingCarte);
        titluTv.setText(listaCarti.get(position).getDenumire());
        rb.setRating((float)listaCarti.get(position).getRating());
        return view;
    }
}
