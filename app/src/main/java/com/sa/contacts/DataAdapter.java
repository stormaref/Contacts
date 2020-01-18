package com.sa.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.contacts.Activities.ShowActivity;

import java.util.List;

public class DataAdapter extends ArrayAdapter<Contact> {
    private Context context;

    public DataAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, R.layout.adapter_item, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, parent, false);
        TextView name = convertView.findViewById(R.id.FullNameLabel);
        Contact contact = getItem(position);
        name.setClickable(true);
        name.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowActivity.class);
            intent.putExtra("id", contact.getId());
            context.startActivity(intent);
        });
        name.setText(String.format("%s %s", contact.FirstName, contact.LastName));
        return convertView;
    }
}
