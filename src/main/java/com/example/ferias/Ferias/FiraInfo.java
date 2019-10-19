package com.example.ferias.Ferias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ferias.Empresas.Empresa;
import com.example.ferias.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FiraInfo extends AppCompatActivity {

    private static final String TAG = "FiraInfo Initialized";

    private ArrayList<Empresa> Empreses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fira_info);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            //Empreses = (ArrayList<Empresa>) extras.getString("Empreses");
        }

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: RecyclerView Empreses");

        RecyclerView recyclerview = findViewById(R.id.firaParentLayout);
        Ferias_Adapter adpt = new Ferias_Adapter(Empreses, this);
        recyclerview.setAdapter(adpt);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}
