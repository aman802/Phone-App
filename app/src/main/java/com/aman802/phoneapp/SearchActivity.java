package com.aman802.phoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private UserRecyclerViewAdapter adapter;
    private ArrayList<User> allContactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        allContactList = Util.getAllContacts(this);
        adapter.updateList(allContactList);
    }

    private void init() {
        ImageView backIcon = findViewById(R.id.search_back_icon);
        final EditText searchEditText = findViewById(R.id.search_edit_text);
        final ImageView clearSearchIcon = findViewById(R.id.search_clear_image_view);

        RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
        adapter = new UserRecyclerViewAdapter(this, allContactList, getLayoutInflater());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        clearSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditText.setText("");
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) clearSearchIcon.setVisibility(View.GONE);
                else clearSearchIcon.setVisibility(View.VISIBLE);

                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        searchEditText.requestFocus();
        Util.showKeyboard(this, searchEditText);
    }

    @Override
    public void onBackPressed() {
        SharedPreference.setSearchActive(this, false);
        super.onBackPressed();
    }
}