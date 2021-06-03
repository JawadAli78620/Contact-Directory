package com.jawadali.i170268;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.OnContactItemListener {

    private FloatingActionButton addBtn, msgbtn;
    private EditText searchEt;
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<ContactDetailsModel> contactDataList;

    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.add_fab);
        msgbtn = findViewById(R.id.message_fab);
        searchEt = findViewById(R.id.searchEt);
        recyclerView = findViewById(R.id.recyclerView);

        //DB Instance
        db = new SQLiteHelper(this);

        contactDataList = new ArrayList<>();

        //Add Fab Click listiner
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivityForResult(intent, 1);
            }
        });

        // Message Fab click listener
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                //putting contacts List into the bundle, as key value pair so you can retrieve array list with key
                bundle.putSerializable("contacts", contactDataList);

                Intent intent = new Intent(MainActivity.this, SendMessage.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //Use Linear Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                //Get Data from Intent
                String name = data.getStringExtra("name");
                String phone = data.getStringExtra("phone");
                String email = data.getStringExtra("email");
                String address = data.getStringExtra("address");
                String img = data.getStringExtra("imageUri");

                //Set Values in Data Array
                /*ContactDetailsModel model = new ContactDetailsModel();
                model.setName(name);
                model.setPhone(phone);
                model.setEmail(email);
                model.setAddress(address);
                model.setImgUri(img);*/

                //contactDataList.add(model);

                //======================== STORE DATA IN SQLite Databse ===================================

                //insert Data
                ContentValues values = new ContentValues();
                values.put(PhoneDirectoryContract.ContactEntry.NAME_COL, name);
                values.put(PhoneDirectoryContract.ContactEntry.PHONE_COL, phone);
                values.put(PhoneDirectoryContract.ContactEntry.ADDRESS_COL, address);
                values.put(PhoneDirectoryContract.ContactEntry.EMAIL_COL, email);
                values.put(PhoneDirectoryContract.ContactEntry.IMAGE_URI_COL, img);
                boolean  id = db.insertContact(values);
                if(!id){
                    Toast.makeText(this, "Error: Can not add contact", Toast.LENGTH_SHORT).show();
                }

                // Retrieve Data
                Cursor result = db.getAllContacts();
                if(result.getCount() == 0){
                    Toast.makeText(this, "No Contact saved in the Directory", Toast.LENGTH_SHORT).show();
                }
                else{
                    StringBuffer stringBuffer = new StringBuffer();
                    while (result.moveToNext()){
                        ContactDetailsModel model = new ContactDetailsModel();
                        model.setId(result.getInt(0));
                        model.setName(result.getString(1));
                        model.setPhone(result.getString(2));
                        model.setAddress(result.getString(3));
                        model.setEmail(result.getString(4));
                        model.setImgUri(result.getString(5));
                        contactDataList.add(model);
                    }
                }

                //=========================================================================================

                //Specify an adapter
                adapter = new MyRecyclerViewAdapter(contactDataList, this);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onContactClick(int position) {

        Intent intent = new Intent(MainActivity.this, ContactDetails.class);
        intent.putExtra("name", contactDataList.get(position).getName());
        intent.putExtra("phone", contactDataList.get(position).getPhone());
        intent.putExtra("email", contactDataList.get(position).getEmail());
        intent.putExtra("address", contactDataList.get(position).getAddress());
        //intent.putExtra("imageUri", contactDataList.get(position).getImgUri());

        startActivity(intent);
    }
}
