package id.reza.healthypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        String nama = getIntent().getExtras().getString("nama");
        String namap = getIntent().getExtras().getString("namap");
        String telepon = getIntent().getExtras().getString("telepon");
        String jk = getIntent().getExtras().getString("jk");
        String jh = getIntent().getExtras().getString("jh");
        String umur = getIntent().getExtras().getString("umur");

        hasilnama = findViewById(R.id.input_nama);
        hasilnamap = findViewById(R.id.input_nmpeliharaan);
        hasiltelepon = findViewById(R.id.input_notelepon);
        hasiljk = findViewById(R.id.input_jeniskelamin);
        hasilumur = findViewById(R.id.angkaumur);
        btn_kembali = findViewById(R.id.btn_kembali);

        hasilnama.setText( "" + nama);
        hasilnamap.setText( "" + namap);
        hasiltelepon.setText( "" + telepon);
        hasiljk.setText( "" + jk);
        hasilumur.setText( "" + umur);

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataPendaftar.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}