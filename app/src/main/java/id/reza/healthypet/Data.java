package id.reza.healthypet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.reza.healthypet.database.DBHelper;

public class Data extends AppCompatActivity {
    RecyclerView recyclerView;
    SQLiteDatabase sqLiteDatabase;
    TextView valid;
    private ArrayList<Model> dataholder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_data);
        DBHelper db = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_data_daftar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DBHelper(this).readalldata();

        while(cursor.moveToNext()){
            Model obj = new Model(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            dataholder.add(obj);
        }

        Adapter adapter = new  Adapter(dataholder, Data.this, sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }
}