package com.bookstore.listview;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity{

    private boolean mTwoPane = false;
    private Book mSelectedBook = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //take view object and put on screen
        // -> android view hierarchy
        //display layout file
        //layout = xml file
        //2 files, android will choose which one depending on resolution

        //android starts -> manifest file -> activity with two properties
        //launcher activity for this app
        //instance of main activity class -> invoke onCreate method
        //when you launch an activity there is a lifecycle that tells you the order of methods

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Shopping Cart");

        int savedBookId = -1;
        if (savedInstanceState != null) {
            savedBookId = savedInstanceState.getInt(BookDetailFragment.EXTRA_BOOK_ID, -1);
        }
    }

    // displays the menu bar with shopping cart
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);

        return true;
    }

    // lets user know they clicked button in menu bar with message
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_shoppingCart:
                Toast.makeText(this, "Shopping Cart selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
