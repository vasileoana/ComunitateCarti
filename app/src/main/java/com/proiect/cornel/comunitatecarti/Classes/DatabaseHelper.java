package com.proiect.cornel.comunitatecarti.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Cornel on 09-Jan-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    String comanda;
    String tableName;
    Context ctx;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String comanda) {
        super(context, name, factory, version);
        this.comanda = comanda;
        this.tableName = name;
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(comanda);
        Toast.makeText(ctx, "onCreateDB", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
        Toast.makeText(ctx, "onUpgradeDb", Toast.LENGTH_SHORT).show();

    }
}
