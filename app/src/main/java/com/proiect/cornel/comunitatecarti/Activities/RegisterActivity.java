package com.proiect.cornel.comunitatecarti.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.proiect.cornel.comunitatecarti.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText etNume = null;
    EditText etPrenume = null;
    EditText etUsername = null;
    EditText etPassword = null;
    EditText etEmail = null;
    EditText etTelefon = null;
    RadioGroup rgSex = null;
    EditText etDataNasterii = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etNume = (EditText) findViewById(R.id.etNume);
        etPrenume = (EditText) findViewById(R.id.etPrenume);
        etUsername = (EditText) findViewById(R.id.etUsernameReg);
        etPassword = (EditText) findViewById(R.id.etParolaReg);
        etEmail = (EditText) findViewById(R.id.etEmailReg);
        etTelefon = (EditText) findViewById(R.id.etTelefonReg);
        rgSex = (RadioGroup) findViewById(R.id.sexReg);
        etDataNasterii = (EditText) findViewById(R.id.etDataNasteriiReg);
    }

    public void btnRegisterClick(View view) {
        int eroare = 0;
        if (etUsername.getText().toString().trim().equals("")) {
            etUsername.setError("Numele utilizatorului este necesar!");
            eroare = 1;
        }
        if (etPassword.getText().toString().trim().equals("")) {
            etPassword.setError("Parola este necesara!");
            eroare = 1;
        }
        if (etEmail.getText().toString().trim().equals("")) {
            etEmail.setError("Email-ul este necesar!");
            eroare = 1;
        }
        if (eroare == 0) {
            String nume;
            if (!etNume.getText().toString().equals("")) {
                nume = etNume.getText().toString();
            }
            else {
                nume = "Anonim";
            }
            String prenume;
            if (!etPrenume.getText().toString().equals("")) {
                prenume = etPrenume.getText().toString();
            }
            else {
                prenume = "Anonim";
            }
            String username = etUsername.getText().toString();
            String parola = etPassword.getText().toString();
            String email = etEmail.getText().toString();
            String telefon;
            if (!etTelefon.getText().toString().equals("")) {
                telefon = etTelefon.getText().toString();
            } else {
                telefon = "000000";
            }
            int id = rgSex.getCheckedRadioButtonId();
            String sex = null;
            if (id != -1) {
                RadioButton r = (RadioButton) rgSex.findViewById(id);
                sex = r.getText().toString();
            } else {
                sex = "NA";
            }

            String dataInput;
            if (!etDataNasterii.getText().toString().equals("")) {
                 dataInput = etDataNasterii.getText().toString();

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date data = new Date();
                try {
                    if (!dataInput.equals("")) {
                        data = formater.parse(dataInput);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                dataInput = "0000.00.00";
            }

            //testare POST catre un server creat in nodejs
            String link = "https://homeworkspace-cornelsora.c9users.io/Users/" +
                    nume + "/" + prenume + "/" + username + "/" + parola +
                    "/" + email + "/" + telefon + "/" + sex + "/" + dataInput;
            ProfilActivity.JsonAccessPost jsonPost = new ProfilActivity.JsonAccessPost();
            URL url = null;
            try {
                url = new URL(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            jsonPost.execute(url);

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(this, "Inregistrare reusita!", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
    }
}
