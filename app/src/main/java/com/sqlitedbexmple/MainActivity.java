package com.sqlitedbexmple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        Contact contact = new Contact();
        contact.phoneNumber = "985457457";
        contact.name = "sreenu";

        databaseHelper.addContact(contact);
        List<Contact> contactList = databaseHelper.readAllContacts();

        databaseHelper.deleteContact("sreenu");
    }
}
