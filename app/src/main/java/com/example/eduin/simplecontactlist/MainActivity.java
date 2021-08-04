package com.example.eduin.simplecontactlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduin.simplecontactlist.database.MyContact;
import com.orm.SugarDb;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdaptor mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView addBtn;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;

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
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count == 1) {
            Toast.makeText(getApplicationContext(), "Press again to exit!", Toast.LENGTH_SHORT).show();
        }
        if (count == 2) {
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search contacts");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
