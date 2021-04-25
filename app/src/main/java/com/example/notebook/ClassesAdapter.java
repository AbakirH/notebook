package com.example.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {

    private Context context;
    private List<Class> aClasses;

    public ClassesAdapter(Context context, List<Class> aClasses) {
        this.context = context;
        this.aClasses = aClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(Class aClass) {
            tvDescription.setText(aClass.getDescription());
            tvClassName.setText(aClass.getClassName());
//            ParseFile image = aClass.getImage();
//            if (image != null) {
//                Glide.with(context).load(aClass.getImage().getUrl()).into(ivImage);
//            }
        }
    }
}