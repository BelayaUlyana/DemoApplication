package com.mycompany.testtask.usersList;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;
import com.mycompany.testtask.usersDetails.DetailsUserActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbarTitle);

        initRecyclerView();

        ListUsersPresenter presenter = new ListUsersPresenter(this);
        presenter.getUserList();
        adapter.setItems(userList);

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListUsersAdapter.OnUserClickListener onUserClickListener = new ListUsersAdapter.OnUserClickListener() {

            @Override
            public void onUserClick(User user) {
                Intent intent = new Intent(ListUsersActivity.this, DetailsUserActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        };

        adapter = new ListUsersAdapter(onUserClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showInfo(List<User> userList) {
        adapter.setItems(userList);
    }

    @Override
    public void showError(String error) {
        Log.e("LOG ERROR ", error);
        Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT).show();
    }

}
