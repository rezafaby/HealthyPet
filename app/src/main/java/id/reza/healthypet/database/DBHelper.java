package id.reza.healthypet.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.reza.healthypet.Data;

public class DBHelper extends SQLiteOpenHelper {
    private final Context context;

    public DBHelper(Context context) {
        super(context, "HealthyPet.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE data_daftar(id INTEGER PRIMARY KEY AUTOINCREMENT, nmpemilik TEXT, nmpeliharaan TEXT, jenis_kelamin TEXT, telepon TEXT, jenis_perawatan TEXT, umur TEXT, textumur TEXT DEFAULT '', is_valid TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS HealthyPet.db");
        onCreate(db);
    }

    public Boolean insert(String nmpemilik, String nmpeliharaan, String telepon, String jenis_kelamin, String jenis_perawatan, String umur, String textumur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nmpemilik", nmpemilik);
        contentValues.put("nmpeliharaan", nmpeliharaan);
        contentValues.put("telepon", telepon);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        contentValues.put("jenis_perawatan", jenis_perawatan);
        contentValues.put("umur", umur);
        contentValues.put("textumur", textumur);
        long result = db.insert("data_daftar", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT*FROM data_daftar ORDER BY telepon ASC");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void deleteData(String telepon) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM data_daftar WHERE telepon='" + telepon + "'");
    }


    public Cursor loadData(String id) {
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM  data_daftar WHERE telepon =?";
        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(id)});
        return cursor;
    }

//    public void updateData(String telepon, String textumur, String is_valid){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("textumur", textumur);
//        cv.put("is_valid", is_valid);
//
//        long result = db.update("data_daftar", cv, "telepon" + "='" + telepon + "'", null);
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Intent intent = new Intent(context, Data.class);
//            context.startActivity(intent);
//        }
//    }

    public Boolean updateData(String nmpemilik, String nmpeliharaan, String telepon, String jenis_kelamin, String jenis_perawatan, String umur, String textumur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nmpemilik", nmpemilik);
        contentValues.put("nmpeliharaan", nmpeliharaan);
        contentValues.put("telepon", telepon);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        contentValues.put("jenis_perawatan", jenis_perawatan);
        contentValues.put("umur", umur);
        contentValues.put("textumur", textumur);
        long result = db.update("data_daftar", contentValues, "telepon" + "=" + telepon, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
