package com.sa.contacts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
    public void onBackPressed() {
        Intent intent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        int code = getIntent().getIntExtra("code", -1);
        setContentView(R.layout.activity_list);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        ListView listView = findViewById(R.id.list);
        TextView countTxt = findViewById(R.id.countTxt);
        EditText search = findViewById(R.id.searchTxt);
        List<Contact> contacts = Contact.listAll(Contact.class);
        List<Contact> tmpList = contacts.stream().filter(Contact::isFav).collect(Collectors.toList());
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = search.getText().toString();
                if (code == 1) {
                    List<Contact> list = contacts.stream().filter(c -> c.getFirstName().contains(str) || c.getLastName().contains(str)).collect(Collectors.toList());
                    Collections.sort(list, (f1, f2) -> f1.getFirstName().compareTo(f2.getFirstName()));
                    listView.setAdapter(new DataAdapter(ListActivity.this, list));
                    countTxt.setText(String.valueOf(list.size()));
                } else {
                    List<Contact> list = tmpList.stream().filter(c -> c.getFirstName().contains(str) || c.getLastName().contains(str)).collect(Collectors.toList());
                    Collections.sort(list, (f1, f2) -> f1.getFirstName().compareTo(f2.getFirstName()));
                    listView.setAdapter(new DataAdapter(ListActivity.this, list));
                    countTxt.setText(String.valueOf(list.size()));
                }
            }
        });
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.this, AddActivity.class);
            intent.putExtra("code", code);
            startActivity(intent);
        });
        Collections.sort(contacts, (f1, f2) -> f1.getFirstName().compareTo(f2.getFirstName()));
        if (code == 1) {
            listView.setAdapter(new DataAdapter(ListActivity.this, contacts));
            countTxt.setText(String.valueOf(contacts.size()));
        } else {
            listView.setAdapter(new DataAdapter(ListActivity.this, tmpList));
            countTxt.setText(String.valueOf(tmpList.size()));
        }
    }
}
