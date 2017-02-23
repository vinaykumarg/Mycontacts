package com.example.vinayg.mycontacts.myapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinayg.mycontacts.R;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    private String phoneNumber;
    private EditText mContactEditText;
    ArrayList<String> aa= new ArrayList<String>();
    private ContactsDataSource mContactsDataSource;
    private AutoCompleteTextView tv;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mContactEditText = (EditText) findViewById(R.id.name);
        tv = (AutoCompleteTextView) findViewById(R.id.phno);
        mSaveButton = (Button) findViewById(R.id.save);
        mSaveButton.setOnClickListener(this);
        ArrayAdapter<String> adapter = getNumber(getContentResolver());
        tv.setAdapter(adapter);
        mContactsDataSource = new ContactsDataSource(getApplicationContext());
        mContactsDataSource.open();
        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                String selected = (String) arg0.getAdapter().getItem(arg2);
                Toast.makeText(getApplicationContext(),
                        "Clicked " + arg2 + " number: " + selected,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public ArrayAdapter<String> getNumber(ContentResolver cr)
    {
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            aa.add(phoneNumber);
        }
        phones.close();// close cursor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,aa);
        return adapter;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                String name = mContactEditText.getText().toString();
                String phone = tv.getText().toString();
                if (name.matches("")||phone.matches("")) {
                    Toast.makeText(this, "You did not enter details correctly", Toast.LENGTH_SHORT).show();
                    return;
                }
                mContactsDataSource.createContact(name,phone);
                Intent intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                finish();
                break;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContactsDataSource.close();
    }
}
