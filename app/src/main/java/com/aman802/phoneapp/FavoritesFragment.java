package com.aman802.phoneapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private Context context;
    private ListScrollInterface mCallback;
    private ConstraintLayout emptyListConstraintLayout;
    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter adapter;
    private ArrayList<User> favoritesList = new ArrayList<>();

    public FavoritesFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallback = (ListScrollInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        context = getContext();
        init(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        favoritesList = Util.getFavoriteContacts(context);
        adapter.updateList(favoritesList);
        if (favoritesList.isEmpty()) {
            emptyListConstraintLayout.setVisibility(View.VISIBLE);
        }
        else {
            emptyListConstraintLayout.setVisibility(View.GONE);
        }
    }

    private void init(View rootView) {
        emptyListConstraintLayout = rootView.findViewById(R.id.favorites_empty_list_constraint_layout);
        recyclerView = rootView.findViewById(R.id.favorites_recycler_view);
        adapter = new UserRecyclerViewAdapter(context, favoritesList, getLayoutInflater());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                mCallback.onListScroll();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}