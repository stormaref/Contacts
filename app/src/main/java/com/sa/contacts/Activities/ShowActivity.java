package com.sa.contacts.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.orm.SugarContext;
import com.sa.contacts.Contact;
import com.sa.contacts.R;
import com.sa.contacts.StaticTools;

import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShowActivity.this, ListActivity.class);
        intent.putExtra("code", 1);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_show);
        long id = getIntent().getLongExtra("id", -1);
        Contact contact = Contact.findById(Contact.class, id);

        TextView Address = findViewById(R.id.showaddress);
        TextView FirstName = findViewById(R.id.showfname);
        TextView Home = findViewById(R.id.showhome);
        TextView HMail = findViewById(R.id.showhmail);
        TextView LastName = findViewById(R.id.showlname);
        TextView Mobile = findViewById(R.id.showmobile);
        TextView Website = findViewById(R.id.showweb);
        TextView WMail = findViewById(R.id.showwmail);
        TextView Work = findViewById(R.id.showwork);
        TextView Birth = findViewById(R.id.showbirth);
        ImageView imageView = findViewById(R.id.showimage);
        Button BlockBtn = findViewById(R.id.AddBlockBtn);
        Button FavBtn = findViewById(R.id.addFavBtn);
        Button CallBtn = findViewById(R.id.CallBtn);
        Button ShareBtn = findViewById(R.id.shareBtn);
        Button DeleteBtn = findViewById(R.id.deleteBtn);

        DeleteBtn.setOnClickListener(v -> {
            contact.delete();
            Intent intent = new Intent(ShowActivity.this, ListActivity.class);
            intent.putExtra("code", 1);
            startActivity(intent);
        });

        BlockBtn.setOnClickListener(v -> {
            if (contact.isBlocked()) {
                BlockBtn.setText("Block");
                contact.setBlocked(false);
                contact.save();
                StaticTools.ToastMaker(ShowActivity.this, "Contact unblocked");
            } else {
                BlockBtn.setText("Unblock");
                contact.setBlocked(true);
                contact.save();
                StaticTools.ToastMaker(ShowActivity.this, "Contact blocked");
            }
        });

        if (contact.isFav()) FavBtn.setText("Unfav");
        else FavBtn.setText("Fav");

        if (contact.isBlocked()) BlockBtn.setText("Unblock");
        else BlockBtn.setText("Block");

        FavBtn.setOnClickListener(v -> {
            if (contact.isFav()) {
                FavBtn.setText("Fav");
                contact.setFav(false);
                contact.save();
                List<Contact> contacts = Contact.listAll(Contact.class);
                StaticTools.ToastMaker(ShowActivity.this, "Contact removed from fav");
            } else {
                FavBtn.setText("Unfav");
                contact.setFav(true);
                contact.save();
                List<Contact> contacts = Contact.listAll(Contact.class);
                StaticTools.ToastMaker(ShowActivity.this, "Contact added to fav");
            }
        });

        ShareBtn.setOnClickListener(v -> {
            Share(String.format("%s %s", contact.getFirstName(), contact.getLastName()), contact.getMobile());
        });

        CallBtn.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + contact.getMobile()));
            if (ContextCompat.checkSelfPermission(ShowActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ShowActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            } else {
                startActivity(callIntent);
            }
        });

        Address.setText(contact.getAddress());
        FirstName.setText(contact.getFirstName());
        Home.setText(contact.getHome());
        HMail.setText(contact.getHomeMail());
        LastName.setText(contact.getLastName());
        Mobile.setText(contact.getMobile());
        Website.setText(contact.getWebsite());
        WMail.setText(contact.getWorkMail());
        Work.setText(contact.getWork());
        Birth.setText(contact.getBirthDate());
        imageView.setImageBitmap(StaticTools.GetImageFromBytes(contact.getImage(), 100, 100));
    }

    public void Share(String Name, String Number) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Name);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Number);
        startActivity(Intent.createChooser(sharingIntent, "Share using:"));
    }
}
