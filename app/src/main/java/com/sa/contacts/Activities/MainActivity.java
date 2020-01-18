package com.sa.contacts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.orm.SugarContext;
import com.sa.contacts.Contact;
import com.sa.contacts.R;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_main);
        Button ContactsBtn = findViewById(R.id.ContactsBtn);
        Button FavBtn = findViewById(R.id.FavBtn);
        final Intent intent = new Intent(MainActivity.this, ListActivity.class);
        ContactsBtn.setOnClickListener(v -> {
            intent.putExtra("code", 1);
            startActivity(intent);
        });
        FavBtn.setOnClickListener(v -> {
            intent.putExtra("code", 2);
            startActivity(intent);
        });
    }
}
