package id.reza.healthypet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.reza.healthypet.database.DBHelper;

public class Data extends AppCompatActivity {
    public static Data ma;
    RecyclerView recyclerView;
    SQLiteDatabase sqLiteDatabase;
    DBHelper database ;
    protected Cursor cursor;
    String[] daftar;
    TextView valid;
    private ArrayList<Model> dataholder = new ArrayList<>();
    Button button_daftar, button_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_data);

        button_daftar = findViewById(R.id.btnbaru);

        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Data.this, MainActivity.class);
                startActivity(i);
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about){
            android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(this);
            builder1.setMessage("Nama :Ni Putu Reza Faby Yolanda\nNIM : 1905551025");
            builder1.setTitle("ABOUT APP");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            android.app.AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if (item.getItemId() == R.id.data) {
            startActivity(new Intent(this, Data.class));
        }
        return true;
    }

}
