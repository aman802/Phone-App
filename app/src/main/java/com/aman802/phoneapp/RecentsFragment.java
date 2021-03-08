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

public class RecentsFragment extends Fragment {
    private Context context;
    private ListScrollInterface mCallback;
    private ConstraintLayout emptyListConstraintLayout;
    private ArrayList<User> recentCallsList = new ArrayList<>();
    private UserRecyclerViewAdapter adapter;

    public RecentsFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallback = (ListScrollInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recents, container, false);
        context = getContext();
        init(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        recentCallsList = Util.getRecentContacts(context);
        adapter.updateList(recentCallsList);
        if (recentCallsList.isEmpty()) {
            emptyListConstraintLayout.setVisibility(View.VISIBLE);
        }
        else {
            emptyListConstraintLayout.setVisibility(View.GONE);
        }
    }

    private void init(View rootView) {
        emptyListConstraintLayout = rootView.findViewById(R.id.recents_empty_list_constraint_layout);
        RecyclerView recyclerView = rootView.findViewById(R.id.recents_recycler_view);
        adapter = new UserRecyclerViewAdapter(context, recentCallsList, getLayoutInflater());
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