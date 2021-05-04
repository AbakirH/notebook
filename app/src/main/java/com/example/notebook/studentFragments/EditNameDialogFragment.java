package com.example.notebook.studentFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Class;
import com.example.notebook.ClassesAdapter;
import com.example.notebook.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// ...

public class EditNameDialogFragment extends DialogFragment {

    public static final String TAG = "EditNameDialogFragment";
    private EditText mEditText;
    private Button joinClass;

    public EditNameDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditNameDialogFragment newInstance(String title) {
        EditNameDialogFragment frag = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.join_class_pop_up, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.class_ID);
        joinClass = view.findViewById(R.id.joinClass);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classID = mEditText.getText().toString();
                if (isNumeric(classID)) {
                    joinClass(classID);
                    Log.i(TAG, "A number was entered");
                } else {
                    Toast.makeText(getContext(), "You have to enter a valid number to join a class", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "A not valid number was entered ");
                }
            }
        });
    }

    private void joinClass(String classID){
        // Refresh page when class is added
        Log.i(TAG, "Test");
        ParseQuery<Class> query = ParseQuery.getQuery(Class.class);
        query.whereEqualTo("classID", Integer.parseInt(classID));
        query.getFirstInBackground(new GetCallback<Class>() {
            @Override
            public void done(Class createdClass, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting the class for the student to join", e);
                    return;
                }else {
                    createdClass.addStudent(ParseUser.getCurrentUser().getUsername(),ParseUser.getCurrentUser().getObjectId());
                    createdClass.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e != null){
                                Log.e(TAG, "Error while saving", e);
                                Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                            }
                            Log.i(TAG, "Class saved successful!");
                            dismiss();
                        }
                    });
                    Log.i(TAG, "Class Name: " + createdClass.getClassName() + " Class Description: " + createdClass.getDescription());
                }
            }
        });
    }

    private static boolean isNumeric(String string) {
        int intValue;

        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}