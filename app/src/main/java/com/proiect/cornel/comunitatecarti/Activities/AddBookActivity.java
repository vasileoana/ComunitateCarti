package com.proiect.cornel.comunitatecarti.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.proiect.cornel.comunitatecarti.Classes.Carte;
import com.proiect.cornel.comunitatecarti.Classes.DialogFragmentAdaugaCarte;
import com.proiect.cornel.comunitatecarti.Classes.Statics;
import com.proiect.cornel.comunitatecarti.R;

public class AddBookActivity extends AppCompatActivity {

    ListView listView = null;
    EditText etAutor = null;
    Spinner spCategorie = null;
    RadioGroup rgDisp = null;
    RadioButton rbDa = null;
    RadioButton rbNu = null;
    RelativeLayout modificari = null;
    Button btnSalvare = null;
    Button btnAdaugaCarte = null;
    int pozitieActuala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        listView = (ListView) findViewById(R.id.idListaCarti);
        etAutor = (EditText) findViewById(R.id.etAddBookAtuori);
        spCategorie = (Spinner) findViewById(R.id.spinnerCategorieCarteAdd);
        rgDisp = (RadioGroup) findViewById(R.id.rgDisp);
        rbDa = (RadioButton) rgDisp.findViewById(R.id.rbDa);
        rbNu = (RadioButton) rgDisp.findViewById(R.id.rbNu);
        btnSalvare = (Button) findViewById(R.id.btnSalvare);
        modificari = (RelativeLayout) findViewById(R.id.modifcariCarte);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                modificari.setVisibility(View.VISIBLE);
                etAutor.setText(Statics.listaMeaCarti.get(i).getAutor());
                if (Statics.listaMeaCarti.get(i).isDisponibilitate()) {
                    rbDa.setChecked(true);
                } else {
                    rbNu.setChecked(true);
                }
                pozitieActuala = i;
            }
        });

        btnAdaugaCarte = (Button)findViewById(R.id.btnAdaugaCarte);
        btnAdaugaCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                DialogFragmentAdaugaCarte dialogFragment = new DialogFragmentAdaugaCarte();
                dialogFragment.show(fragmentManager,"Fragment Manager");
            }
        });

        btnSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String autor = etAutor.getText().toString();
                String categorie = spCategorie.getSelectedItem().toString();
                boolean disponibilitate;
                if (rbDa.isChecked()) {
                    disponibilitate = true;
                } else {
                    disponibilitate = false;
                }
                Carte carteActuala = Statics.listaMeaCarti.get(pozitieActuala);
                carteActuala.setAutor(autor);
                carteActuala.setCategorie(categorie);
                carteActuala.setDisponibilitate(disponibilitate);
                modificari.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
