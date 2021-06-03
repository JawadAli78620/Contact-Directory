package com.jawadali.i170268;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class ContactDetails extends AppCompatActivity {

    private CircularImageView mProfileImage;
    private TextView mName, mPhone, mEmail, mAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        mName = findViewById(R.id.details_name_Tv);
        mPhone = findViewById(R.id.details_phone_Tv);
        mEmail = findViewById(R.id.details_email_Tv);
        mAddress = findViewById(R.id.details_address_Tv);
        mProfileImage = findViewById(R.id.details_Iv);

        Intent intent = getIntent();
        mName.setText(intent.getStringExtra("name"));
        mPhone.setText(intent.getStringExtra("phone"));
        mEmail.setText(intent.getStringExtra("email"));
        mAddress.setText(intent.getStringExtra("address"));
       // Uri uri = Uri.parse(intent.getStringExtra("imageUri"));
       // mProfileImage.setImageURI(uri);

    }
}
