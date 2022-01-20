package com.sample.dodo;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.sample.dodo.adapter.MainToDoRecyclerViewAdapter;
import com.sample.dodo.data.ToDo;
import com.sample.dodo.data.ToDoDatabase;
import com.sample.dodo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    ArrayList<String> list = new ArrayList<>();
    ToDoDatabase db = ToDoDatabase.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        getSupportActionBar().setTitle(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setList();
        RecyclerView recyclerView = findViewById(R.id.mainToDoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        MainToDoRecyclerViewAdapter adapter = new MainToDoRecyclerViewAdapter(list) ;
        recyclerView.setAdapter(adapter) ;
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));

        db.toDoDao().getAll().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(List<ToDo> toDos) {
                list.add(toDos.toString());
            }
        });
    }

    void setList() {
        list.add("시험공부");
        list.add("장보기");
        list.add("PT 신청하기");
        list.add("기획하기");
        list.add("방 청소하기");
        list.add("샴푸 사기");
        list.add("으아아");
        list.add("으아dkdkdkd아");
        list.add("으아아아아아아ㅏㅏㅏㅏㅏㅏㅏㅏ아");
        list.add("으아ㄴ엉나ㅣ오아아아앙아");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}