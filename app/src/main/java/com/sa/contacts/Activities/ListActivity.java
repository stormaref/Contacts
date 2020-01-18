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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListActivity extends AppCompatActivity {

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
        List<Contact> contacts = Contact.listAll(Contact.class);
        Collections.sort(contacts, (f1, f2) -> f1.toString().compareTo(f2.toString()));
        List<Contact> tmpList;
        if (getIntent().getIntExtra("code", -1) == 1) {
            tmpList = Contact.listAll(Contact.class);
        } else {
            tmpList = contacts.stream().filter(Contact::isFav).collect(Collectors.toList());
        }
        listView.setAdapter(new DataAdapter(ListActivity.this, tmpList));
    }
}
