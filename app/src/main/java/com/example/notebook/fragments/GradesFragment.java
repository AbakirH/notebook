package com.example.notebook.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.notebook.Class;
import com.example.notebook.PostsAdapter;
import com.example.notebook.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.instgramclone.fragments.PostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradesFragment extends Fragment {

    public static final String TAG = "ClassesFragment";
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Class> allClasses;

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    // Clean all elements of the recycler
}