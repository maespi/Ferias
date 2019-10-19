package com.example.ferias;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferias.Empresas.Empresa;
import com.example.ferias.Ferias.Feria;
import com.example.ferias.Ferias.FiraInfo;

import java.util.ArrayList;

public class MainMenu_Adapter extends RecyclerView.Adapter<MainMenu_Adapter.ViewHolderMainMenu> {
    private static final String TAG = "MainMenu_Adapter";

    private ArrayList<Feria> sFiraNames = new ArrayList<>();
    private ArrayList<Empresa> Empreses = new ArrayList<>();
    private Context context;

    public MainMenu_Adapter(ArrayList<Feria> sFiraNames,ArrayList<Empresa> Empreses , Context context) {
        this.sFiraNames = sFiraNames;
        this.Empreses = Empreses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMainMenu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fira_listitem, viewGroup, false);
        ViewHolderMainMenu viewHolder = new ViewHolderMainMenu(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderMainMenu viewHolderMainMenu, int i) {

        viewHolderMainMenu.firaName.setText(sFiraNames.get(i).getName());
        viewHolderMainMenu.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,viewHolderMainMenu.firaName.getText(),Toast.LENGTH_SHORT).show();

                Intent empIntent = new Intent(context, FiraInfo.class);
                empIntent.putExtra("empreses", Empreses);
                context.startActivity(empIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sFiraNames.size();
    }

    public class ViewHolderMainMenu extends RecyclerView.ViewHolder{

        TextView firaName;
        RelativeLayout itemLayout;

        public ViewHolderMainMenu(@NonNull View itemView) {
            super(itemView);
            firaName = itemView.findViewById(R.id.firaName);
            itemLayout = itemView.findViewById(R.id.nameParent);
        }
    }
}
