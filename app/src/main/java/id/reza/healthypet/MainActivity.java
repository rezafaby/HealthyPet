package id.reza.healthypet;

import static java.lang.String.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

public class MainActivity extends AppCompatActivity{

    //Initilize Variable
    EditText input_nama, input_peliharaan, input_telpon;
    RadioGroup radioGroup;
    RadioButton jenis_kelamin;
    CheckBox cb1, cb2;
    SeekBar skumur;
    TextView angkaumur;
    Button btn_daftar;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);


        input_nama = findViewById(R.id.input_nmpemilik);
        input_peliharaan = findViewById(R.id.input_nmpeliharaan);
        input_telpon = findViewById(R.id.input_notelepon);
        radioGroup = findViewById(R.id.radio_jenkel);
        cb1 = findViewById(R.id.check_grooming);
        cb2 = findViewById(R.id.check_potong);
        skumur = findViewById(R.id.seekbar_umur);
        angkaumur = findViewById(R.id.umur);
        btn_daftar = findViewById(R.id.button_daftar);

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

        btn_daftar.setOnClickListener(new View.OnClickListener() {
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

                String txt_nmpemilik =  input_nama.getText().toString();
                String txt_nmpeliharaan =  input_peliharaan.getText().toString();
                String txt_telepon =  input_telpon.getText().toString();
                String txt_jenkel = jenis_kelamin.getText().toString();
                String txt_jenper = jenis_perawatan;
                String txt_umur = String.valueOf(skumur.getProgress());

                Boolean checkinsert = db.insert(txt_nmpemilik, txt_nmpeliharaan, txt_telepon, txt_jenkel, txt_jenper, txt_umur, "0");
                if(checkinsert == true){
                    String finalJenis_perawatan = jenis_perawatan;
                    AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Selamat Pendaftaran Berhasil!"+'\n')
                            .setMessage("Nama pemilik        : " +
                                    txt_nmpemilik+'\n'+"Nama peliharaan  : " +
                                    txt_nmpeliharaan+'\n'+"No telepon             : " +
                                    txt_telepon+'\n' + "Jenis kelamin        : " +
                                    txt_jenkel+'\n' + "Jenis perawatan   : " +
                                    txt_jenper+'\n'+"Umur peliharaan   : "+
                                    txt_umur + " bulan")
                            .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(MainActivity.this, DataPendaftar.class);
                                    i.putExtra("nama", input_nama.getText().toString());
                                    i.putExtra("namap", input_peliharaan.getText().toString());
                                    i.putExtra("telepon", input_telpon.getText().toString());
                                    i.putExtra("jk", jenis_kelamin.getText().toString());
                                    i.putExtra("jp", finalJenis_perawatan);
                                    i.putExtra("umur", angkaumur.getText().toString());
                                    startActivity(i);

                                }
                            }).create();
                    alert.show();
                }else{
                    Toast.makeText(MainActivity.this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
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