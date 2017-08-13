package com.example.masood.simplecontactlist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

/**
 * Created by masood on 8/13/17.
 */

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
