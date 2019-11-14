package com.mycompany.testtask.usersList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;

import java.util.ArrayList;
import java.util.List;

public class ListUsersActivity extends AppCompatActivity implements ListUsersContract {
    private List<User> userList = new ArrayList<>();
    private ListUsersAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbarTitle);

        ListUsersPresenter presenter = new ListUsersPresenter(this);
        presenter.getUserList();
        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);

        adapter = new ListUsersAdapter(this, userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showInfo(List<User> userList) {
        adapter.setUserList(userList);
    }

    @Override
    public void showError(String error) {
        Log.e("LOG ERROR ", error);
        Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT).show();
    }
}
