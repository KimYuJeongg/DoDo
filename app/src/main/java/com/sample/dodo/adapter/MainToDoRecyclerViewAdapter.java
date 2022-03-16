package com.sample.dodo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainToDoRecyclerViewAdapter extends RecyclerView.Adapter<MainToDoRecyclerViewAdapter.ViewHolder> {

    private List<ToDo> items = null;
    private ToDoDatabase db;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView toDoContents, dDay;
        ImageView currentState, importanceImage;
        int index;
        Date getToday;

        //      TODO: use resource array
        int[] importanceImageArray = {R.drawable.ic_importance, R.drawable.ic_importance1, R.drawable.ic_importance2, R.drawable.ic_importance3};
        int[] stateImageColorArray = {R.drawable.state1, R.drawable.state2, R.drawable.state3, R.drawable.state4};
        String[] stateStringArray = {"시작 전으로", "진행 중으로", "중지로", "완료로"};
        String[] dialogString = {"수정", "삭제"};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            toDoContents = itemView.findViewById(R.id.toDoContentsItem);
            dDay = itemView.findViewById(R.id.dDay);
            currentState = itemView.findViewById(R.id.currentState);
            importanceImage = itemView.findViewById(R.id.importanceImage);
            getToday = new Date();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(() -> {
                        int currentStateNum = items.get(index).getCurrentState();
                        if (currentStateNum < 3) {
                            currentStateNum += 1;
                        } else {
                            currentStateNum = 0;
                        }
                        items.get(index).setCurrentState(currentStateNum);
                        currentState.setImageResource(stateImageColorArray[currentStateNum]);
                        final int stateIndex = currentStateNum;

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(v.getContext(), "현재 상태를 " + (stateStringArray[stateIndex]) + " 변경합니다.", Toast.LENGTH_LONG).show();
                            }
                        }, 0);
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
                            new Thread(() -> {
                                if (which == 0) {
                                } else {
                                    db.toDoDao().delete(items.get(index));
                                }
                            }).start();
                        }
                    });
                    builder.show();
                    return true;
                }
            });
        }

        public void onBind(ToDo todo, int position) {
            index = position;
            toDoContents.setText(todo.getContents());
            if (todo.getDeadline() != null) {
                try {
                    String format = new SimpleDateFormat("yyyy.MM.dd").format(getToday);
                    Date today = new SimpleDateFormat("yyyy.MM.dd").parse(format);
                    Date deadline = new SimpleDateFormat("yyyy.MM.dd").parse(todo.getDeadline());
                    long diffSec = (deadline.getTime() - today.getTime()) / 1000;
                    long diffDays = diffSec / (24 * 60 * 60);

                    if (diffDays == 0) {
                        dDay.setText("Today");
                    } else {
                        dDay.setText("D - " + diffDays);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            currentState.setImageResource(stateImageColorArray[todo.getCurrentState()]);
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
        holder.onBind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
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
