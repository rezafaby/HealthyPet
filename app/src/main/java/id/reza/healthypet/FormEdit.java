package id.reza.healthypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.sql.Types.NULL;

import id.reza.healthypet.database.DBHelper;

public class FormEdit extends AppCompatActivity {
    private ArrayList<Model> dataholder = new ArrayList<>();
    DBHelper db;
    SQLiteDatabase sqLiteDatabase;
    EditText input_keterangan;
    TextView txt_nmpemilik, txt_nmpeliharaan, txt_telepon, txt_jenkel, txt_perawatan, txt_umur;
    Button button_update;
    Button button_baru;
    RadioGroup radioGroup;
    RadioButton radioButton;

    String nmpeliharaan, keterangan, is_valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_form);
        button_baru = findViewById(R.id.btnbaru);

        button_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormEdit.this, MainActivity.class);
                startActivity(intent);
            }
        });

        DBHelper db = new DBHelper(this);

        findid();

        if (getIntent().getBundleExtra("datadaftar") != null) {
            Bundle bundle = getIntent().getBundleExtra("datadaftar");
            txt_nmpemilik.setText(bundle.getString("nmpemilik"));
            txt_nmpeliharaan.setText(bundle.getString("nmpeliharaan"));
            txt_telepon.setText(bundle.getString("telepon"));
            txt_jenkel.setText(bundle.getString("jenis_kelamin"));
            txt_perawatan.setText(bundle.getString("jenis_perawatan"));
            txt_umur.setText(bundle.getString("umur"));
            input_keterangan.setText(bundle.getString("textketerangan"));

            //get radio
            RadioButton radValid = (RadioButton)findViewById(R.id.yes);
            RadioButton radTidak = (RadioButton)findViewById(R.id.no);

            if ("1".equals(bundle.getString("is_valid"))){
                radValid.setChecked(true);
            } else if ("0".equals(bundle.getString("is_valid"))){
                radTidak.setChecked(true);
            } else {
                radValid.setChecked(false);
                radTidak.setChecked(false);
            }
        }

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = findViewById(R.id.radio_valid);
                input_keterangan = findViewById(R.id.input_keterangan);

                int valid = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(valid);

                String value = "";
                if (radioButton.getText().toString().equals("Tolak")) {
                    value = "0";
                }else {
                    value = "1";
                }

                DBHelper db = new DBHelper(FormEdit.this);
                is_valid = radioButton.getText().toString();
                keterangan = input_keterangan.getText().toString();
                Bundle bundle = getIntent().getBundleExtra("datadaftar");
                db.updateData(bundle.getString("telepon"), keterangan, value);
            }
        });
    }

    private void findid() {
        txt_nmpemilik = findViewById(R.id.txt_nik);
        txt_nmpeliharaan = findViewById(R.id.txt_nama);
        txt_telepon = findViewById(R.id.txt_telepon);
        txt_jenkel = findViewById(R.id.txt_jenkel);
        txt_perawatan = findViewById(R.id.txt_kondisi);
        txt_umur = findViewById(R.id.txt_persentase);
        input_keterangan = findViewById(R.id.input_keterangan);
        button_update = findViewById(R.id.button_update);
    }
}

