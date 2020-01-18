package com.sa.contacts.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orm.SugarContext;
import com.sa.contacts.Contact;
import com.sa.contacts.R;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_show);
        long id = getIntent().getLongExtra("id", -1);
        TextView textView = findViewById(R.id.ShowNameLabel);
        Contact contact = Contact.findById(Contact.class, id);
        textView.setText(String.format("Name: %s %s", contact.getFirstName(), contact.getLastName()));
    }
}
