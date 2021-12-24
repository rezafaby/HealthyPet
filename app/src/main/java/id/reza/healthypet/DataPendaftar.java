package id.reza.healthypet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataPendaftar extends AppCompatActivity {

    private TextView hasilnama, hasilnamap, hasiltelepon, hasiljk, hasiljh, hasilumur;
    private Button btn_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datapendaftar);

//        String nama = getIntent().getExtras().getString("nama");
//        String namap = getIntent().getExtras().getString("namap");
//        String telepon = getIntent().getExtras().getString("telepon");
//        String jk = getIntent().getExtras().getString("jk");
//        String jh = getIntent().getExtras().getString("jh");
//        String umur = getIntent().getExtras().getString("umur");

        hasilnama = findViewById(R.id.input_nama);
        hasilnamap = findViewById(R.id.input_nmpeliharaan);
        hasiltelepon = findViewById(R.id.input_notelepon);
        hasiljk = findViewById(R.id.input_jeniskelamin);
        hasiljh = findViewById(R.id.input_perawatan);
        hasilumur = findViewById(R.id.angkaumur);
        btn_kembali = findViewById(R.id.btn_kembali);

        Intent i =getIntent();
        hasilnama.setText(i.getExtras().getString("nama"));
        hasilnamap.setText(i.getExtras().getString("namap"));
        hasiltelepon.setText(i.getExtras().getString("telepon"));
        hasiljk.setText(i.getExtras().getString("jk"));
        hasiljh.setText(i.getExtras().getString("jp"));
        hasilumur.setText(i.getExtras().getString("umur"));

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataPendaftar.this, MainActivity.class);
                startActivity(i);
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
