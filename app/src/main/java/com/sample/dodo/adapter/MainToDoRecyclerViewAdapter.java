package com.sample.dodo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.dodo.R;
import com.sample.dodo.data.ToDo;
import com.sample.dodo.data.ToDoDatabase;

import java.util.List;

public class MainToDoRecyclerViewAdapter extends RecyclerView.Adapter<MainToDoRecyclerViewAdapter.ViewHolder> {

    private List<ToDo> mData = null;
    private ToDoDatabase db;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView toDoContents, dDay;
        ImageView currentState, importanceImage;
        int index;
        int[] importanceImageArray = { R.drawable.ic_importance, R.drawable.ic_importance1, R.drawable.ic_importance2, R.drawable.ic_importance3 };

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            toDoContents = itemView.findViewById(R.id.toDoContentsItem);
            dDay = itemView.findViewById(R.id.dDay);
            currentState = itemView.findViewById(R.id.currentState);
            importanceImage = itemView.findViewById(R.id.importanceImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("Recyclerview", "position = "+ getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("Recyclerview", "position = "+ getAdapterPosition());
                    return false;
                }
            });
        }

        public void onBind(ToDo todo, int position) {
            index = position;
            toDoContents.setText(todo.getContents());
            dDay.setText(todo.getDeadline().toString());
            currentState.setColorFilter(Color.parseColor(todo.getCurrentState()));
            importanceImage.setImageResource(importanceImageArray[todo.getImportance()]);
        }

    }

    public MainToDoRecyclerViewAdapter(ToDoDatabase db) {
        this.db = db;
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
//        holder.toDoContents.setText(mData.get(position));
        holder.onBind(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
