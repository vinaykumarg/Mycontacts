package com.example.vinayg.mycontacts.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.vinayg.mycontacts.helper.Contact;
import com.example.vinayg.mycontacts.helper.MyDataHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vinay.g.
 */

public class ContactsDataSource {
    private SQLiteDatabase database;
    private MyDataHelper dbHelper;
    private String[] allColumns = { MyDataHelper.COLUMN_ID,
            MyDataHelper.COLUMN_NAME, MyDataHelper.COLUMN_PHONUMBER };

    public ContactsDataSource(Context context) {
        dbHelper = new MyDataHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Contact createContact(String name, String Phone) {
        ContentValues values = new ContentValues();
        values.put(MyDataHelper.COLUMN_NAME, name);
        values.put(MyDataHelper.COLUMN_PHONUMBER , Phone);
        long insertId = database.insert(MyDataHelper.CONTACTS, null,
                values);
        Cursor cursor = database.query(MyDataHelper.CONTACTS,
                allColumns, MyDataHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Contact newContact = cursorToContact(cursor);
        cursor.close();
        return newContact;
    }

    public void deleteContact(Contact contact) {
        long id = contact.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MyDataHelper.CONTACTS, MyDataHelper.COLUMN_ID
                + " = " + id, null);
    }
    public Cursor getcurser(){
        Cursor cursor = database.query(MyDataHelper.CONTACTS,
                allColumns, null, null, null, null, null);
        return cursor;
    }
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();

        Cursor cursor = database.query(MyDataHelper.CONTACTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = cursorToContact(cursor);
            contacts.add(contact);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return contacts;
    }

    private Contact cursorToContact(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getLong(0));
        contact.setName(cursor.getString(1));
        contact.setPhnumber(cursor.getString(2));
        return contact;
    }
}
