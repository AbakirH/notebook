package com.example.notebook.studentFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.notebook.ClassesAdapter;
import com.example.notebook.R;
import com.example.notebook.createClass;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.example.notebook.Class;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ClassesFragment extends Fragment {

    public static final String TAG = "ClassesFragment";
    private SwipeRefreshLayout swipeContainer;
    private Button btnJoinClass;
    protected List<Class> allClasses;
    private RecyclerView rvClasses;
    protected ClassesAdapter adapter;


    public ClassesFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.student_fragment_classes, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnJoinClass = view.findViewById(R.id.btnJoinClass);
        allClasses = new ArrayList<>();
        rvClasses = view.findViewById(R.id.rvClasses);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        adapter = new ClassesAdapter(getContext(), allClasses);
        rvClasses.setAdapter(adapter);
        rvClasses.setLayoutManager(new LinearLayoutManager(getContext()));
        queryClasses();

        btnJoinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goCreateActivity();
            }
        });

    }

    protected void queryClasses() {
        // Specify which class to query
        Class createdClass = new Class();
        ParseQuery<Class> query = ParseQuery.getQuery(Class.class);
        query.whereContains("teacherClass", ParseUser.getCurrentUser().getObjectId());
        query.setLimit(20);
        query.addDescendingOrder(Class.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Class>() {
            @Override
            public void done(List<Class> Classes, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Class createdClass: Classes) {
                    Log.i(TAG, "Class Name: " + createdClass.getClassName() + "Class Description: " + createdClass.getDescription());
//                    Toast.makeText(getContext(), "Class Name: " + createdClass.getClassName()
//                            + "Class Description: " + createdClass.getDescription(), Toast.LENGTH_LONG).show();
                }
                allClasses.addAll(Classes);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void goCreateActivity() {
        Intent i = new Intent(getContext(), createClass.class);
        startActivity(i);
        getActivity().finish();
    }


}