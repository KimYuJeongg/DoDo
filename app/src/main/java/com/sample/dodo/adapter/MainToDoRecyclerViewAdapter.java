package com.sample.dodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.dodo.R;

import java.util.ArrayList;

public class MainToDoRecyclerViewAdapter extends RecyclerView.Adapter<MainToDoRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView toDoContents, dDay;
        ImageView currentState, importanceImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            toDoContents = itemView.findViewById(R.id.toDoContentsItem);
            dDay = itemView.findViewById(R.id.dDay);
            currentState = itemView.findViewById(R.id.currentState);
            importanceImage = itemView.findViewById(R.id.importanceImage);
        }
    }

    public MainToDoRecyclerViewAdapter(ArrayList<String> list) {
        mData = list;
    }

    @NonNull
    @Override
    public MainToDoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.todolist_item, parent, false);
        MainToDoRecyclerViewAdapter.ViewHolder vh = new MainToDoRecyclerViewAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainToDoRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.toDoContents.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
