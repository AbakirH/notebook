package com.example.notebook;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {

    private Context context;
    private List<Class> aClasses;
    private boolean teacherUser = false;
    private Button classDetails;

    public ClassesAdapter(Context context, List<Class> aClasses) {
        this.context = context;
        this.aClasses = aClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(ParseUser.getCurrentUser().get("Role").equals("Teacher")){
            teacherUser = true;
            view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        }else {
            teacherUser = false;
            view = LayoutInflater.from(context).inflate(R.layout.student_item_class, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Class aClass = aClasses.get(position);
        holder.bind(aClass);
    }

    @Override
    public int getItemCount() {
        return aClasses.size();
    }

    public void clear() {
        aClasses.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Class> list) {
        aClasses.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvClassName;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView classID;
        private TextView numStudents;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            if(teacherUser){
                classID = itemView.findViewById(R.id.classID);
                numStudents = itemView.findViewById(R.id.numStudents);
                classDetails = itemView.findViewById(R.id.classDetails);
            }
        }

        public void bind(Class aClass) {
            tvDescription.setText("Description: " + aClass.getDescription());
            tvClassName.setText(aClass.getClassName());
            if(teacherUser){
                classID.setText("Class ID: " + aClass.getClassID());
                numStudents.setText("Number of Students in the Class: " + aClass.getStudents().size());
                classDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                        goCreateActivity(v, aClass.getClassName(), aClass.getStudents().size() , aClass.getStudents());
                    }
                });
            }
//            ParseFile image = aClass.getImage();
//            if (image != null) {
//                Glide.with(context).load(aClass.getImage().getUrl()).into(ivImage);
//            }
        }
        private void goCreateActivity(View view, String className, int size, List<String> students) {
            Intent i = new Intent(view.getContext(), ClassDetails.class);
            i.putExtra("className", className);
            i.putExtra("numStudents", size);
            i.putExtra("students", Parcels.wrap(students));
            view.getContext().startActivity(i);
        }
    }


}