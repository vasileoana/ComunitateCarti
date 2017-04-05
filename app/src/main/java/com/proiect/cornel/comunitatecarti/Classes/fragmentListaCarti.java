package com.proiect.cornel.comunitatecarti.Classes;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.proiect.cornel.comunitatecarti.Classes.Carte;
import com.proiect.cornel.comunitatecarti.Classes.Statics;
import com.proiect.cornel.comunitatecarti.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class fragmentListaCarti extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listacarti, null);

        ArrayList<String> listaCarti = new ArrayList<>();

        for (int i = 0; i < Statics.listaMeaCarti.size(); i++) {
            Carte carte = Statics.listaMeaCarti.get(i);
                listaCarti.add(carte.getDenumire());
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        listaCarti) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextSize(20);
                        text.setTextColor(Color.BLACK);
                        return view;
                    }
                };
        ListView listView = (ListView) view.findViewById(R.id.idListaCarti);

        listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        listView.setAdapter(adapter);
        return view;

    }
}
