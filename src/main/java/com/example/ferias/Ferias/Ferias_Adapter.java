package com.example.ferias.Ferias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferias.Empresas.Empresa;
import com.example.ferias.R;

import java.util.ArrayList;

public class Ferias_Adapter extends RecyclerView.Adapter<Ferias_Adapter.ViewHolderFerias> {
    private static final String TAG = "Ferias_Adapter";

    private ArrayList<Empresa> Empresas = new ArrayList<>();
    private Context context;

    public Ferias_Adapter(ArrayList<Empresa> empresas, Context context) {
        Empresas = empresas;
        this.context = context;
    }

    @NonNull
    @Override
    public Ferias_Adapter.ViewHolderFerias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fira_listitem, parent, false);
        ViewHolderFerias viewHolder = new ViewHolderFerias(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Ferias_Adapter.ViewHolderFerias holder, int i) {
        holder.empresaName.setText(Empresas.get(i).getName());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.empresaName.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Empresas.size();
    }

    public class ViewHolderFerias extends RecyclerView.ViewHolder{

        TextView empresaName;
        RelativeLayout itemLayout;

        public ViewHolderFerias(@NonNull View itemView) {
            super(itemView);
            empresaName = itemView.findViewById(R.id.firaName);
            itemLayout = itemView.findViewById(R.id.nameParent);
        }
    }
}
