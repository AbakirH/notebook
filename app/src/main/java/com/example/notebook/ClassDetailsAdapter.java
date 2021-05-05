package com.example.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassDetailsAdapter extends RecyclerView.Adapter<ClassDetailsAdapter.ViewHolder>{
    private Context context;
    private List<Student> aClasses;
    private boolean teacherUser = false;
    private Button classDetails;

    public ClassDetailsAdapter(Context context, List<Student> aClasses) {
        this.context = context;
        this.aClasses = aClasses;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClassDetailsAdapter.ViewHolder holder, int position) {
        Student aClass = aClasses.get(position);
        holder.bind(aClass);
    }

    @Override
    public int getItemCount() {
        return aClasses.size();
    }

    public void clear() {
        aClasses.clear();
//        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Student> list) {
        aClasses.addAll(list);
//        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView tvStudentname;
        private TextView tvGrade;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentname = itemView.findViewById(R.id.tvStudentName);
            tvGrade = itemView.findViewById(R.id.studentGrade);

        }

        public void bind(Student aClass) {
            tvStudentname.setText(aClass.getName());
            tvGrade.setText("Grade: " + aClass.getGrade());
//            ParseFile image = aClass.getImage();
//            if (image != null) {
//                Glide.with(context).load(aClass.getImage().getUrl()).into(ivImage);
//            }
        }
//            ParseFile image = aClass.getImage();
//            if (image != null) {
//                Glide.with(context).load(aClass.getImage().getUrl()).into(ivImage);
//            }
        }


}
