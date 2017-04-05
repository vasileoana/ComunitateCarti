package com.proiect.cornel.comunitatecarti.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proiect.cornel.comunitatecarti.Classes.Carte;
import com.proiect.cornel.comunitatecarti.Classes.DBAdapter;
import com.proiect.cornel.comunitatecarti.Classes.JsonAccessGet;
import com.proiect.cornel.comunitatecarti.Classes.JsonBookGet;
import com.proiect.cornel.comunitatecarti.Classes.Statics;
import com.proiect.cornel.comunitatecarti.Classes.User;
import com.proiect.cornel.comunitatecarti.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister = null;
    private Button btnLogin = null;
    private EditText etUserName = null;
    private EditText etParola = null;
    private String username = null;
    private String pass = null;
    boolean validUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Statics.listaMeaCarti = new ArrayList<>();
        Statics.listaTotalaCarti = new ArrayList<>();
        setContentView(R.layout.activity_main);
        accesJson();
        btnRegister = (Button) findViewById(R.id.btnRegister);
        etUserName = (EditText) findViewById(R.id.etUser);
        etParola = (EditText) findViewById(R.id.etPassword);
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        pass = sharedPreferences.getString("password", "");
        etUserName.setText(username);
        etParola.setText(pass);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Statics.userCurent = null;
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MeniuPrincipalActivity.class);
                if (Statics.verificaUser(etUserName.getText().toString(), etParola.getText().toString())) {
                    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("username", etUserName.getText().toString());
                    editor.putString("password", etParola.getText().toString());
                    editor.apply();
                    validUser = true;
                }

                Statics.initCartiUser();
                if (Statics.useri.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Ne pare rau, dar e o problema de retea. Va rog, verificati conexiunea la internet si reporniti aplicatia", Toast.LENGTH_SHORT).show();
                } else {
                    if (validUser) {
                        Toast.makeText(getApplicationContext(), Statics.userCurent.toString(), Toast.LENGTH_SHORT).show();
                        etUserName.setText("");
                        etParola.setText("");
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Parola sau username introduse incorect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void accesJson() {
        AssetManager assetManager = getApplicationContext().getAssets();
        String linkCarti = null;
        String linkUsers = null;
        try {
            InputStream inputStream = assetManager.open("app.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            linkUsers = properties.getProperty("linkServerUsers");
            linkCarti = properties.getProperty("linkServerCarti");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonAccessGet jsonGet = new JsonAccessGet() {
            @Override
            protected void onPostExecute(Void aVoid) {
                createTableUsers();
            }
        };
        jsonGet.execute(linkUsers);
        JsonBookGet jsonBookGet = new JsonBookGet() {
            @Override
            protected void onPostExecute(Void aVoid) {
                createTableBooks();
            }
        };
        jsonBookGet.execute(linkCarti);
    }

    public void createTableUsers() {
        try {
            String command = "CREATE TABLE Users(id INTEGER PRIMARY KEY AUTOINCREMENT, nume TEXT, prenume TEXT, username TEXT, " +
                    "parola TEXT, email TEXT, telefon TEXT, sex TEXT, dataNasterii TEXT);";
            DBAdapter dbAdapter = new DBAdapter(getApplicationContext(), "Users", command);
            dbAdapter.openConnection();
            for (User user : Statics.useri) {
                dbAdapter.insertUsers(user);
            }
            List<User> userList;
            userList = dbAdapter.getUsers();
            if (Statics.useri.size() == 0) {
                Statics.useri = userList;
            }
            dbAdapter.closeConnection();
        } catch (Exception ex) {
        }
    }

    public void createTableBooks() {
        try {
            String command = "CREATE TABLE Books(id INTEGER PRIMARY KEY AUTOINCREMENT, denumire TEXT, autor TEXT, categorie TEXT, " +
                    "rating REAL, disponibilitate TEXT, idUtilizator INTEGER);";
            DBAdapter dbAdapter = new DBAdapter(getApplicationContext(), "Books", command);
            dbAdapter.openConnection();
            for (Carte carte : Statics.listaTotalaCarti) {
                dbAdapter.insertBook(carte);
            }
            List<Carte> bookList;
            bookList = dbAdapter.getBooks();
        /*for (Carte book : bookList) {
            Toast.makeText(this, book.toString(), Toast.LENGTH_SHORT).show();
        }*/

            if (Statics.listaTotalaCarti.size() == 0) {
                Statics.listaTotalaCarti = bookList;
            }
            dbAdapter.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
