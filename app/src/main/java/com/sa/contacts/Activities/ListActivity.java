package com.sa.contacts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;
import com.sa.contacts.Contact;
import com.sa.contacts.DataAdapter;
import com.sa.contacts.R;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_list);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        ListView listView = findViewById(R.id.list);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.this, AddActivity.class);
            startActivity(intent);
        });
        listView.setAdapter(new DataAdapter(ListActivity.this, contacts));
    }
}
