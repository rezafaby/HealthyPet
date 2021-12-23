package id.reza.healthypet;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.reza.healthypet.database.DBHelper;

public class FormEdit extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper database;
    Button btn_simpan;
    EditText nmpemilik, nmpeliharaan, telepon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_form);
        database = new DBHelper(this);
        nmpemilik = findViewById(R.id.edit_nmpemilik);
        nmpeliharaan = findViewById(R.id.edit_nmpeliharaan);
        telepon = findViewById(R.id.edit_nmpeliharaan);
        btn_simpan = findViewById(R.id.button_simpanedit);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_daftar WHERE telepon = '" +
                getIntent().getStringExtra("telepon") + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            nmpemilik.setText(cursor.getString(0).toString());
            nmpeliharaan.setText(cursor.getString(1).toString());
            telepon.setText(cursor.getString(2).toString());

        }
        btn_simpan.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update animal set nama= '" +
                        nmpemilik.getText().toString() + "', nmpemilik = '" +
                        telepon.getText().toString() + "' where telepon ='" +
                        nmpeliharaan.getText().toString() + "', nmpeliharaan = '" +
                        getIntent().getStringExtra("telepon") + "'");
                Toast.makeText(FormEdit.this, "Data Berhasil Dipdate", Toast.LENGTH_SHORT).show();
                finish();
            }
        }));
    }
}