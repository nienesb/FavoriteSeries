package com.example.jboeser.favoriteseries;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private EditText mEditText;
    private ArrayAdapter<String> mAdapter;
    private List<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DbHelper mDbHelper = new DbHelper((ApplicationContextProvider) getApplicationContext());

        //list view
        mListView = (ListView) findViewById(R.id.list_view);
        mItems = new ArrayList<>();

        //Initialize the views
        mEditText = (EditText) findViewById(R.id.add_item_edit_text);
        registerForContextMenu(mListView);

        //shows message when clicked on an item
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clickedItem = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(MainActivity.this, "Clicked on: " + clickedItem, Toast.LENGTH_SHORT).show();
            }
        });

        //shows new entry on list
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    //gather the text that the user entered and add it to the ListView as a new item
    private void addItem() {
        String newItem = mEditText.getText().toString();
        if (!newItem.isEmpty()) {
            mItems.add(newItem);
            mEditText.setText("");
            updateUI();
        } else {
            Toast.makeText(this, "Please enter a value in the text field.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        //Get the clicked item
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //Get the name of the clicked item
        String clickedItem = (String) mListView.getItemAtPosition(info.position);
        //Inflate the context menu from the resource file
        getMenuInflater().inflate(R.menu.context_menu, menu);
        //Find the delete MenuItem by its ID
        MenuItem deleteButton = menu.findItem(R.id.context_menu_delete_item);
        //Get the title from the menu button
        String originalTitle = deleteButton.getTitle().toString();
        //Make a new title combining the original title and the name of the clicked list item
        deleteButton.setTitle(originalTitle + " '" + clickedItem + "'?");
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Retrieve info about the long pressed list item
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.context_menu_delete_item) {
            //Remove the item from the list
            mItems.remove(itemInfo.position);
            //Update the adapter to reflect the list change
            updateUI();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    // will handle all the actions needed to update the ui
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_item) {
            mItems.clear();
            updateUI();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
