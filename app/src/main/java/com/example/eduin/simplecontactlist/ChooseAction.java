package com.example.eduin.simplecontactlist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;



public class ChooseAction extends AppCompatDialogFragment {
    private String[] options;
    private Context context;
    private long id;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        options = new String[]{"Make a Call","Send a SMS"};
        builder.setTitle("Choose an Action!")
                .setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();

    }
}
