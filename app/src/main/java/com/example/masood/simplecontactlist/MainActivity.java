package com.example.masood.simplecontactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.masood.simplecontactlist.database.MyContact;
import com.example.masood.simplecontactlist.database.contactModel;
import com.orm.SugarDb;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarDb db = new SugarDb(this);
        db.onCreate(db.getDB());

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        List<MyContact> input = MyContact.listAll(MyContact.class);
        mAdapter = new MyAdaptor(input,getApplicationContext());
        recyclerView.setAdapter(mAdapter);

        addBtn = (ImageView) findViewById(R.id.add_new_contact_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = AddContactActivity.getActivityIntent(getApplication(),-1);
                startActivity(i);
            }
        });
    }



}
