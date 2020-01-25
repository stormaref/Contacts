package com.sa.contacts.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orm.SugarContext;
import com.sa.contacts.Contact;
import com.sa.contacts.R;
import com.sa.contacts.StaticTools;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {
    byte[] bytes = null;
    private static final int RESULT_LOAD_IMG = 2;
    ImageView imageView;
    int code;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddActivity.this, ListActivity.class);
        intent.putExtra("code", code);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_add);
        code = getIntent().getIntExtra("code", -1);
        Button AddBtn = findViewById(R.id.AddBtn);
        Button AddImageBtn = findViewById(R.id.addImageBtn);
        final EditText fnameTxt = findViewById(R.id.fnameTxt);
        final EditText lnameTxt = findViewById(R.id.lnameTxt);
        final EditText mobileTxt = findViewById(R.id.mobileTxt);
        final EditText homeTxt = findViewById(R.id.homeTxt);
        final EditText workTxt = findViewById(R.id.workTxt);
        final EditText hMailTxt = findViewById(R.id.hMailTxt);
        final EditText wMailTxt = findViewById(R.id.wMailTxt);
        final EditText addressTxt = findViewById(R.id.addressTxt);
        final EditText WebTxt = findViewById(R.id.WebTxt);
        final EditText birthTxt = findViewById(R.id.birthTxt);
        imageView = findViewById(R.id.imageView);

        AddBtn.setOnClickListener(v -> {
            Contact contact = new Contact(fnameTxt.getText().toString(), lnameTxt.getText().toString(),
                    addressTxt.getText().toString(), WebTxt.getText().toString(), mobileTxt.getText().toString(),
                    homeTxt.getText().toString(), workTxt.getText().toString(), hMailTxt.getText().toString(), wMailTxt.getText().toString(),birthTxt.getText().toString(), bytes);
            contact.setFav(code == 2);
            contact.save();
            StaticTools.ToastMaker(AddActivity.this, "Successfully added");
            Intent intent = new Intent(AddActivity.this, ListActivity.class);
            intent.putExtra("code", 1);
            this.startActivity(intent);
        });

        AddImageBtn.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                bytes = StaticTools.GetBytesFromImage(selectedImage, 1);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                StaticTools.ToastMaker(AddActivity.this, "Something went wrong");
            }

        } else {
            StaticTools.ToastMaker(AddActivity.this, "You haven't picked Image");
        }
    }
}
