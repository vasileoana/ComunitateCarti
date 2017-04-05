package com.proiect.cornel.comunitatecarti.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cornel on 09-Jan-17.
 */

public class DBAdapter {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public DBAdapter(Context context, String name, String command) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context, name, null, 1, command);
    }

    public void openConnection() {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void closeConnection() {
        sqLiteDatabase.close();
    }

    public void insertUsers(User user) {
        ContentValues cv = new ContentValues();
        cv.put("id", user.getId());
        cv.put("nume", user.getNume());
        cv.put("prenume", user.getPrenume());
        cv.put("username", user.getUsername());
        cv.put("parola", user.getParola());
        cv.put("email", user.getEmail());
        cv.put("telefon", user.getTelefon());
        cv.put("sex", user.getSex());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //cv.put("dataNasterii", user.getDataNasterii().toString());
        cv.put("dataNasterii", format.format(user.getDataNasterii()));
        sqLiteDatabase.insert("Users", null, cv);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("Users", new String[]{"id", "nume", "prenume", "username", "parola", "email", "telefon", "sex", "dataNasterii"}, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            Date data = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                data = format.parse(cursor.getString(8));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7), data);
            users.add(user);
        } while (cursor.moveToNext());
        return users;
    }

    public void insertBook(Carte carte) {
        ContentValues cv = new ContentValues();
        cv.put("id", carte.getIdCarte());
        cv.put("denumire", carte.getDenumire());
        cv.put("autor", carte.getAutor());
        cv.put("categorie", carte.getCategorie());
        cv.put("rating", carte.getRating());
        String disp;
        if (carte.isDisponibilitate()) {
            disp = "1";
        } else {
            disp = "0";
        }
        cv.put("disponibilitate", disp);
        cv.put("idUtilizator", carte.getIdUtilizator());
        sqLiteDatabase.insert("Books", null, cv);
    }

    public List<Carte> getBooks() {
        List<Carte> books = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("Books", new String[]{"id", "denumire", "autor", "categorie", "rating", "disponibilitate", "idUtilizator"}, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            boolean disp = false;
            if (cursor.getString(5).equals("1")) {
                disp = true;
            }
            Carte carte = new Carte(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getDouble(4), disp, cursor.getInt(6));
            books.add(carte);
        } while (cursor.moveToNext());
        return books;
    }
}
