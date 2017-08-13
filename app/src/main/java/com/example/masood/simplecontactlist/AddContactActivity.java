package com.example.masood.simplecontactlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.masood.simplecontactlist.database.Contact;
import com.example.masood.simplecontactlist.database.MyContact;
import com.orm.SchemaGenerator;

import java.util.List;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, detail, number;
    private Button saveBtn;
    private TextView tv;
    private long id;
    private MyContact mContact;

    private static final String EXTRA_MESSAGE_ID = "contactId";
    public static Intent getActivityIntent(Context cntx, long id) {
        Intent i = new Intent(cntx,AddContactActivity.class);
        i.putExtra(EXTRA_MESSAGE_ID,id);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        name   = (EditText) findViewById(R.id.add_ac_name_et);
        detail = (EditText) findViewById(R.id.add_ac_detail_et);
        number = (EditText) findViewById(R.id.add_ac_number_et);
        saveBtn = (Button) findViewById(R.id.add_ac_save_btn);
        tv = (TextView) findViewById(R.id.tempTEXT);

        Intent i = getIntent();
        id = i.getLongExtra(EXTRA_MESSAGE_ID,-1);
        if(id!=-1) {
            mContact = MyContact.findById(MyContact.class,id);
            name.setText(mContact.getContactName());
            detail.setText(mContact.getContactDetails());
            number.setText(mContact.getContactNumber());
        }
        saveBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
//                Log.wtf("INPUT",name.getText().toString()+ " - " + detail.getText().toString() + " - " + number.getText().toString());
        if(id==-1) {
            mContact = new MyContact(name.getText().toString(),detail.getText().toString(),number.getText().toString());
        } else {
            mContact.setContactName(name.getText().toString());
            mContact.setContactDetails(detail.getText().toString());
            mContact.setContactNumber(number.getText().toString());
        }
        mContact.save();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
