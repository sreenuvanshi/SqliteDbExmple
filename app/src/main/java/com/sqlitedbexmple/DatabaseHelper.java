package com.sqlitedbexmple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static int CONTACT_VER = 1;
    public static String CONTACT_DB = "contact_db";
    public static String CONTACTS_TABLE = "contacts_table";

    public static String PHONE_NUMBER = "phone_number";
    public static String NAME = "name";
    public static String KEY_ID = "key_id";

    public DatabaseHelper(@Nullable Context context) {
        super(context, CONTACT_DB, null, CONTACT_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + CONTACTS_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + PHONE_NUMBER + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //insert
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, contact.name); // Contact Name
        values.put(PHONE_NUMBER, contact.phoneNumber); // Contact Phone

        db.insert(CONTACTS_TABLE, null, values);
        db.close();
    }

    public List<Contact> readAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + CONTACTS_TABLE +" ORDER BY "+NAME+" DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.id = Integer.parseInt(cursor.getString(0));
                contact.name = cursor.getString(1);
                contact.phoneNumber = cursor.getString(2);
                // Adding contact to list
                contactList.add(contact);

            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return contactList;

    }

    public int updateContact(Contact contact, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, contact.name);
        values.put(PHONE_NUMBER, contact.phoneNumber);

        // updating row
        return db.update(CONTACTS_TABLE, values, NAME + " = ?",
                new String[]{String.valueOf(name)});
    }

    public void deleteContact(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONTACTS_TABLE, NAME + " = ?",
                new String[]{String.valueOf(name)});
        db.close();
    }

}
