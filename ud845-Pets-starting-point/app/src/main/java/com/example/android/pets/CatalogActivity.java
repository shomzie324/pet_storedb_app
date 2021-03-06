package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetContract.PetEntry;
import com.example.android.pets.data.PetDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        displayDatabaseInfo();
    }

    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        //PetDbHelper mDbHelper = new PetDbHelper(this);


        // Create any variables needed for db query and create cursor object to hold query result set
       String[] projection = {
         PetEntry._ID,
         PetEntry.COLUMN_PET_NAME,
         PetEntry.COLUMN_PET_BREED,
         PetEntry.COLUMN_PET_GENDER,
         PetEntry.COLUMN_PET_WEIGHT
       };

        /** Execute query via the ContentResolver --> PetProvider --> mDbHelper --> database file*/
       Cursor cursor = getContentResolver().query(PetEntry.CONTENT_URI,projection,null,null,null);

            /** Find ListView layout with its id*/
            ListView listView = (ListView)findViewById(R.id.list);

        /** create instance of PetCursorAdapter*/
        PetCursorAdapter petCursorAdapter = new PetCursorAdapter(this,cursor);

        /** set the adapter on the ListView*/
        listView.setAdapter(petCursorAdapter);

        /** Set empty view when there are no pets by referencing the ID of the XML layout*/
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    private void insertPet(){

        /** Create map of column values with ContentValues class*/
        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME,"Toto");
        values.put(PetEntry.COLUMN_PET_BREED,"Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER,PetEntry.GENDER_MALE );
        values.put(PetEntry.COLUMN_PET_WEIGHT,"7kg");

        /** call the content resolver to call the PetProvider which will process the insert action*/
        Uri resultUri = getContentResolver().insert(PetEntry.CONTENT_URI,values);

        if(resultUri == null){
            Toast.makeText(this,R.string.save_error, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,R.string.save_sucess, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // insert dummy pet data
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}