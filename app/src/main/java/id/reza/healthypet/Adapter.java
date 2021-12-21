package id.reza.healthypet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.Normalizer;
import java.util.ArrayList;

import id.reza.healthypet.database.DBHelper;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

    ArrayList<Model> dataholder = new ArrayList<>();
    Context context;
    SQLiteDatabase sqLiteDatabase;
    TextView is_valid;

    public Adapter(ArrayList<Model> dataholder, Context context, SQLiteDatabase sqLiteDatabase) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.nmpemilik.setText(dataholder.get(position).getNmpemilik());
        holder.nmpeliharaan.setText(dataholder.get(position).getNmpeliharaan());
        holder.telepon.setText(dataholder.get(position).getTelepon());
        holder.is_valid.setText(dataholder.get(position).getIs_valid());

        if("1".equals(dataholder.get(position).getIs_valid())){
            holder.is_valid.setText("Data Valid");
            holder.is_valid.setTextColor(Color.rgb(0,128,0));
        }
        else if("0".equals(dataholder.get(position).getIs_valid())){
            holder.is_valid.setText("Data Tidak Valid");
            holder.is_valid.setTextColor(Color.rgb(255,0,0));
        }

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Konfirmasi Hapus");
                alertDialog.setMessage("Yakin ingin menghapus data?");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        DBHelper db = new DBHelper(context);
                        db.deleteData(dataholder.get(position).getTelepon());

                        dataholder.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, dataholder.size());
                        notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

        holder.button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nmpemilik", dataholder.get(position).getNmpemilik());
                bundle.putString("nmpeliharaan", dataholder.get(position).getNmpeliharaan());
                bundle.putString("telepon", dataholder.get(position).getTelepon());
                bundle.putString("jenis_kelamin", dataholder.get(position).getJenis_kelamin());
                bundle.putString("jenis_perawatan", dataholder.get(position).getJenis_perawatan());
                bundle.putString("umur", dataholder.get(position).getUmur());
                bundle.putString("textumur", dataholder.get(position).getTextumur());
                bundle.putString("is_valid", dataholder.get(position).getIs_valid());

                Intent intent = new Intent(context, FormEdit.class);
                intent.putExtra("datadaftar", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder == null ? 0 : dataholder.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView nmpemilik, nmpeliharaan, telepon, is_valid;
        Button button_delete, button_update;
        public viewholder(@NonNull View itemView){
            super(itemView);
            nmpemilik = (TextView) itemView.findViewById(R.id.nmpemilik);
            nmpeliharaan = (TextView) itemView.findViewById(R.id.nmpeliharaan);
            telepon = (TextView) itemView.findViewById(R.id.telepon);
            is_valid = (TextView) itemView.findViewById(R.id.is_valid);
            button_delete = (Button) itemView.findViewById(R.id.button_delete);
            button_update = (Button) itemView.findViewById(R.id.button_update);
        }
    }
}

