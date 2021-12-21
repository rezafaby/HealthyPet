package id.reza.healthypet;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;

import id.reza.healthypet.database.DBHelper;

public class MainActivity extends AppCompatActivity {
    static int kondisi_kesehatan=0;
    Button button_daftar;
    EditText nmpemilik, nmpeliharaan, telepon;
    CheckBox cb1, cb2;
    RadioGroup radioGroup;
    RadioButton jenis_kelamin;
    SeekBar skumur;
    TextView angkaumur;
    String keterangan;
    String is_valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);

        keterangan = " ";
        is_valid = "0";
        nmpemilik = findViewById(R.id.input_nmpemilik);
        nmpeliharaan = findViewById(R.id.input_nmpeliharaan);
        telepon = findViewById(R.id.input_notelepon);
        button_daftar = findViewById(R.id.button_daftar);
        radioGroup = findViewById(R.id.radio_jenkel);
        cb1 = findViewById(R.id.check_grooming);
        cb2 = findViewById(R.id.check_potong);
        skumur = findViewById(R.id.seekbar_umur);
        angkaumur = findViewById(R.id.umur);
        skumur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                angkaumur.setText(progress + (" bulan"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_jenkel = radioGroup.getCheckedRadioButtonId();
                jenis_kelamin = findViewById(selected_jenkel);

                String jenis_perawatan = "";
                if(cb1.isChecked() && cb2.isChecked()){
                    jenis_perawatan += "Grooming, Potong rambut";
                }
                else{
                    if(cb1.isChecked()) {
                        jenis_perawatan += "Grooming";
                    }
                    if(cb2.isChecked()) {
                        jenis_perawatan += "Potong rambut";
                    }
                }

                String txt_nmpemilik =  nmpemilik.getText().toString();
                String txt_nmpeliharaan =  nmpeliharaan.getText().toString();
                String txt_telepon =  telepon.getText().toString();
                String txt_jenkel = jenis_kelamin.getText().toString();
                String txt_jenper= jenis_perawatan;
                String txt_umur = String.valueOf(skumur.getProgress());

                Boolean checkinsert = db.insert(txt_nmpemilik, txt_nmpeliharaan, txt_telepon, txt_jenkel, txt_jenper, txt_umur, " ", "0");
                if(checkinsert == true){
                    Toast.makeText(MainActivity.this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
                }

                nmpemilik.setText(null);
                nmpeliharaan.setText(null);
                telepon.setText(null);
                radioGroup.clearCheck();
                cb1.setChecked(false);
                cb2.setChecked(false);
                skumur.setProgress(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Nama : Ni Putu Reza Faby Yolanda\nNIM : 1905551025");
            builder1.setTitle("ABOUT APP");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

        } else if (item.getItemId() == R.id.data) {
            Intent viewData = new Intent(MainActivity.this, Data.class);
            startActivity(viewData);

        }else if (item.getItemId() == R.id.logout){
            //
        }
        return true;
    }
}