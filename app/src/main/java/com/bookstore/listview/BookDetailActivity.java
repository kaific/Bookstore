package com.bookstore.listview;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        int book_id = intent.getIntExtra(BookDetailFragment.EXTRA_BOOK_ID, -1);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.book_details_one_pane, BookDetailFragment.newInstance(book_id));
        ft.commit();
    }
}
