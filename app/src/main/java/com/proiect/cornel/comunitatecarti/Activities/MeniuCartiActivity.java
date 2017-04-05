package com.proiect.cornel.comunitatecarti.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.proiect.cornel.comunitatecarti.Classes.ListaCartiAdapter;
import com.proiect.cornel.comunitatecarti.Classes.Statics;
import com.proiect.cornel.comunitatecarti.R;

public class MeniuCartiActivity extends AppCompatActivity {

    ListView listViewCarti = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_carti);
        listViewCarti = (ListView) findViewById(R.id.listaCartiId);
        listViewCarti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), BookDetailsActivity.class);
                intent.putExtra("pozitie", i);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListaCartiAdapter adapter = new ListaCartiAdapter(getApplicationContext(),
        R.layout.lista_carti_custom,
        Statics.listaTotalaCarti);
        listViewCarti.setAdapter(adapter);
    }
}
