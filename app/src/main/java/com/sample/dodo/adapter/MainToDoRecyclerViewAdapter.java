package com.sample.dodo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.dodo.R;
import com.sample.dodo.data.ToDo;
import com.sample.dodo.data.ToDoDatabase;

import java.util.List;

public class MainToDoRecyclerViewAdapter extends RecyclerView.Adapter<MainToDoRecyclerViewAdapter.ViewHolder> {

    private List<ToDo> items = null;
    private ToDoDatabase db;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView toDoContents, dDay;
        ImageView currentState, importanceImage;
        int index;

//      TODO: use resource array
        int[] importanceImageArray = {R.drawable.ic_importance, R.drawable.ic_importance1, R.drawable.ic_importance2, R.drawable.ic_importance3};
        int[] stateColorArray = {R.color.state_blue, R.color.state_green, R.color.state_yellow, R.color.state_red};
        int[] stateStringArray = {R.string.before_start, R.string.progressing, R.string.pause, R.string.complete};
        String[] dialogString = {"수정", "삭제"};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            toDoContents = itemView.findViewById(R.id.toDoContentsItem);
            dDay = itemView.findViewById(R.id.dDay);
            currentState = itemView.findViewById(R.id.currentState);
            importanceImage = itemView.findViewById(R.id.importanceImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(() -> {
                        int currentStateNum = items.get(index).getCurrentState();
                        if(currentStateNum < 3) {
                            currentStateNum += 1;
                        } else {
                            currentStateNum = 0;
                        }
                        items.get(index).setCurrentState(currentStateNum);
                        currentState.setColorFilter(Color.parseColor(Integer.toString(stateColorArray[currentStateNum])));
                        Toast.makeText(v.getContext(), "현재 상태를 " + stateStringArray[currentStateNum] + " 변경합니다.", Toast.LENGTH_LONG).show();
                    }).start();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setItems(dialogString, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which == 0) {

                            } else {

                            }
                        }
                    });
                    return true;
                }
            });
        }

        public void onBind(ToDo todo, int position) {
            index = position;
            toDoContents.setText(todo.getContents());
//            TODO: if(dDay != null) setTextSize(17)
//            dDay.setText(todo.getDeadline().toString());
//            currentState.setColorFilter((stateColorArray[todo.getCurrentState()]));
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
        holder.onBind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        if(items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    public List<ToDo> getItems() {
        return items;
    }


    public void setItem(List<ToDo> data) {
        items = data;
        notifyDataSetChanged();
    }
}
