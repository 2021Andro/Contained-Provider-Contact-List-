package com.example.containedproviderdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnContact(View view) {

        getPhoneConnected();
    }

    public void getPhoneConnected(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);

        }

        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        Log.d(TAG, "Before "+Integer.toString(cursor.getCount()));

        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {

                String contactName = String.valueOf(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactPhoneNumber = String.valueOf(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                Log.d(TAG, "Name: "+contactName+" || Phone number: "+contactPhoneNumber);


            }
        }
    }
}