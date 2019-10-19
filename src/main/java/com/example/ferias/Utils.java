package com.example.ferias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public abstract class Utils {

    static String getMessageInput(Context context, View view, String title, String question){
        final String[] m_Text = {""};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.text_input_dialog, (ViewGroup) view, false);

        final EditText input = (EditText) viewInflated.findViewById(R.id.inputText);
        input.setText(question);

        builder.setView(viewInflated);

// Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                m_Text[0] = input.getText().toString();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

        return m_Text[0];
    }
}
