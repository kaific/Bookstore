package com.bookstore.bookshop;

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

public class MainActivity extends AppCompatActivity implements BookListFragment.ItemListener {

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
        actionBar.setTitle("Bookstore");

        int savedBookId = -1;
        if (savedInstanceState != null) {
            savedBookId = savedInstanceState.getInt(BookDetailFragment.EXTRA_BOOK_ID, -1);
        }

        List<Book> books = Model.getInstance(this).getBooks();
        if (!books.isEmpty()) {
            if (savedBookId == -1) {
                mSelectedBook = Model.getInstance(this).getBooks().get(0);
            } else {
                mSelectedBook = Model.getInstance(this).findBookById(savedBookId);
            }
        }

        mTwoPane = (findViewById(R.id.book_details_two_pane) != null); //true or false depending on whether two-pane or not
        //grab linear layout view object, if not null it's found
        if (mTwoPane && mSelectedBook != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.book_details_two_pane, BookDetailFragment.newInstance(mSelectedBook.getId())); //add into framelayout
            ft.commit(); //screen updates once it's committed
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

    @Override
    public void itemSelected(Book b) {
        mSelectedBook = b;
        if (mTwoPane) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.book_details_two_pane, BookDetailFragment.newInstance(b.getId()));
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra(BookDetailFragment.EXTRA_BOOK_ID, b.getId());
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (mSelectedBook != null) {
            savedInstanceState.putInt(BookDetailFragment.EXTRA_BOOK_ID, mSelectedBook.getId());
        }
        super.onSaveInstanceState(savedInstanceState);
    }
}
