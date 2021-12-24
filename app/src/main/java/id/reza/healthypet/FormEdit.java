package id.reza.healthypet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import id.reza.healthypet.database.DBHelper;

public class FormEdit extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper database;
    Button btn_simpan;
    EditText nmpemilik, nmpeliharaan, telepon;
    EditText input_nama, input_peliharaan, input_telpon;
    String id;
    RadioGroup radioGroup;
    RadioButton jenis_kelamin, jantanRadio, betinaRadio;
    CheckBox cb1, cb2;
    SeekBar skumur;
    TextView angkaumur;
    Button btn_daftar;
    public static MainActivity ma;
    String jenis_perawatan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);

        input_nama = findViewById(R.id.input_nmpemilik);
        input_peliharaan = findViewById(R.id.input_nmpeliharaan);
        input_telpon = findViewById(R.id.input_notelepon);
        radioGroup = findViewById(R.id.radio_jenkel);
        jantanRadio = findViewById(R.id.male);
        betinaRadio = findViewById(R.id.female);
        cb1 = findViewById(R.id.check_grooming);
        cb2 = findViewById(R.id.check_potong);
        skumur = findViewById(R.id.seekbar_umur);
        angkaumur = findViewById(R.id.umur);
        btn_daftar = findViewById(R.id.button_daftar);
        Bundle bundle = getIntent().getBundleExtra("datadaftar");
        id = bundle.getString("id");
        input_nama.setText(bundle.getString("nmpemilik"));
        input_peliharaan.setText(bundle.getString("nmpeliharaan"));
        input_telpon.setText(bundle.getString("telepon"));
        skumur.setProgress(Integer.parseInt(bundle.getString("umur")));
        angkaumur.setText(bundle.getString("umur"));

//        Log.v("babi", bundle.getString("jenis_kelamin"));


        if(bundle.getString("jenis_kelamin").equals(jantanRadio.getText().toString())){
            radioGroup.check(R.id.male);
        }else if(bundle.getString("jenis_kelamin").equals(betinaRadio.getText().toString())){
            radioGroup.check(R.id.female);
        }

        String[] jenisPerawatans = bundle.getString("jenis_perawatan").split(", ");
        if(jenisPerawatans.length == 2){
//            Log.v("babi", "masuk sii");
            jenis_perawatan = "Grooming, Potong rambut";
            cb2.setChecked(true);
            cb1.setChecked(true);
        }
        else{
            if(bundle.getString("jenis_perawatan").equals("Grooming")) {
                jenis_perawatan = "Grooming";
                cb1.setChecked(true);
            }
            if(bundle.getString("jenis_perawatan").equals("Potong rambut")) {
                jenis_perawatan = "Potong rambut";
                cb2.setChecked(true);
            }
        }

        skumur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                angkaumur.setText(progress + (" Bulan"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btn_daftar.setText("BUAT PERUBAHAN");
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_jenkel = radioGroup.getCheckedRadioButtonId();
                jenis_kelamin = findViewById(selected_jenkel);

//                String jenis_perawatan = "";
                if(cb1.isChecked() && cb2.isChecked()){
                    jenis_perawatan = "Grooming, Potong rambut";
                }
                else{
                    if(cb1.isChecked()) {
                        jenis_perawatan = "Grooming";
                    }
                    if(cb2.isChecked()) {
                        jenis_perawatan = "Potong rambut";
                    }
                }

                String txt_nmpemilik =  input_nama.getText().toString();
                String txt_nmpeliharaan =  input_peliharaan.getText().toString();
                String txt_telepon =  input_telpon.getText().toString();
                String txt_jenkel = jenis_kelamin.getText().toString();
                String txt_jenper = jenis_perawatan;
                String txt_umur = String.valueOf(skumur.getProgress());

                Boolean checkinsert = db.updateData(Integer.valueOf(id), txt_nmpemilik, txt_nmpeliharaan, txt_telepon, txt_jenkel, txt_jenper, txt_umur, "0");
                if(checkinsert == true){
                    Toast.makeText(FormEdit.this, "Perubahan berhasil", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(FormEdit.this, Data.class);
                    startActivity(i);
                }else{
                    Toast.makeText(FormEdit.this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
            builder1.setMessage("Nama :Ni Putu Reza Faby Yolanda\nNIM : 1905551025\n\n" +
                    "Aplikasi HealthyPet merupakan sebuah aplikasi android yang digunakan " +
                    "untuk melakukan pendaftaran grooming untuk hewan peliharaan ke Bali Pet Care and Service");
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