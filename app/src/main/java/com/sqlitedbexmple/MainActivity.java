package com.sqlitedbexmple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

        final EditText editNameObj = findViewById(R.id.editName);
        final EditText editEmailObj = findViewById(R.id.editEmail);
        final EditText editPhoneNumberObj = findViewById(R.id.editPhoneNumber);
        final Button btnAddContactObj = findViewById(R.id.btnAddContact);
        final Button btnViewAllContactsObj = findViewById(R.id.btnViewAllContacts);


        btnAddContactObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNameObj.getText().toString())) {
                    editNameObj.setError("Enter contact name");
                    editNameObj.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(editEmailObj.getText().toString())) {
                    editEmailObj.setError("Enter email");
                    editEmailObj.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(editPhoneNumberObj.getText().toString())) {
                    editPhoneNumberObj.setError("Enter phone number");
                    editPhoneNumberObj.requestFocus();
                    return;
                }
                if (editPhoneNumberObj.getText().toString().length() < 9) {
                    editPhoneNumberObj.setError("Enter valid phone number");
                    editPhoneNumberObj.requestFocus();
                    return;
                }
                ///all pass
                Contact contact = new Contact();
                contact.name = editNameObj.getText().toString();
                contact.phoneNumber = editPhoneNumberObj.getText().toString();
                Toast.makeText(getApplicationContext(),"Contact added successfully",Toast.LENGTH_SHORT).show();
                databaseHelper.addContact(contact);


            }
        });


        btnViewAllContactsObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Contact> contactsList = databaseHelper.readAllContacts();
            }
        });

    }
}
