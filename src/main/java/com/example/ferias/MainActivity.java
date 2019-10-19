package com.example.ferias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.ferias.Empresas.Empresa;
import com.example.ferias.Ferias.Feria;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<Feria> sNames = new ArrayList<>();
    private ArrayList<Empresa> Empreses = new ArrayList<>();
    private FirebaseFirestore db;
    private DocumentReference FeriasRef;
    private  MainMenu_Adapter adptFira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMessageInput(view.getContext(), findViewById(R.id.mainLayout), "Afegir Fira", "Nom Fira" );
            }
        });

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();

        fires_data();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Init RecyclerView");

        RecyclerView firesRecyclerView = findViewById(R.id.FiresMainMenu);
        adptFira = new MainMenu_Adapter(sNames, Empreses, this);
        firesRecyclerView.setAdapter(adptFira);
        firesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void fires_data(){
        /*db.collection("ferias").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Feria live = document.toObject(Feria.class);
                        sNames.add(live);
                        Log.d(TAG, document.getId() + " => " + live.getName());
                    }
                    adptFira.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }

        });*/

        db.collection("ferias").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException e) {
                if (e == null) {
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED:
                                Feria live = dc.getDocument().toObject(Feria.class);
                                sNames.add(live);
                                Log.d(TAG, "Nova fira: " + dc.getDocument().getData());
                                break;
                            case MODIFIED:
                                Log.d(TAG, "Fira Modificada: " + dc.getDocument().getData());
                                break;
                            case REMOVED:
                                //TODO:Crear process eliminació
                                Log.d(TAG, "Fira Eliminada: " + dc.getDocument().getData());
                                break;
                        }
                    }
                    adptFira.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "Error getting documents: ", e);
                }
            }
        });
    }


    /////////////////// Feria Name Input /////////////////

    void getMessageInput(Context context, View view, String title, String question){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.text_input_dialog, (ViewGroup) view, false);

        final EditText input = (EditText) viewInflated.findViewById(R.id.inputText);
        input.setHint(question);

        builder.setView(viewInflated);

// Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String m_Text = input.getText().toString();
                Feria new_feria = new Feria(m_Text);

                db.collection("ferias").add(new_feria).addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
    ///////////////////
}
