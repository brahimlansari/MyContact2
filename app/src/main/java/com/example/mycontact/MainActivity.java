package com.example.mycontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public Adapter mAdapter;
    public static final int CONTACTLOADER = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,Editor.class);
                startActivity(intent);
            }
        });
        ListView listView = findViewById(R.id.list);
        mAdapter = new Adapter(this,null);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( MainActivity.this,Editor.class);
                Uri newuri = ContentUris.withAppendedId(Contract.ContactEntry.CONTENT_URI,id);
                intent.setData(newuri);
                startActivity(intent);
            }
        });

        LoaderManager.getInstance(this).initLoader(CONTACTLOADER,null,null);
    }



    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {Contract.ContactEntry._ID,
                Contract.ContactEntry.COLUMN_NAME,
                Contract.ContactEntry.COLUMN_EMAIL,
                Contract.ContactEntry.COLUMN_PICTURE,
                Contract.ContactEntry.COLUMN_PHONENUMBER,
                Contract.ContactEntry.COLUMN_TYPEOFCONTACT
        };
        return new  androidx.loader.content.CursorLoader(this,Contract.ContactEntry.CONTENT_URI,projection,null,null,null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
         mAdapter.swapCursor(null);
    }
}