package com.jawadali.i170268;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class SendMessage extends AppCompatActivity {

    ArrayList<ContactDetailsModel> contactDetailsList;

    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        Bundle bundle = getIntent().getExtras();
        contactDetailsList = (ArrayList<ContactDetailsModel>) bundle.getSerializable("contacts");

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.msg_recipient_Et);
        autoCompleteTextView.setAdapter(adapter);*/
        //System.out.println("NAME:  " + contactDetailsList.get(0).getName());

    }
}
