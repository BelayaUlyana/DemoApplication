package com.mycompany.testtask.usersList;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;
import com.mycompany.testtask.usersDetails.DetailUserActivity;
import com.mycompany.testtask.usersDetails.DetailUserFragment;

public class ListUserActivity extends AppCompatActivity implements ListUserFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    @Override
    public void onFragmentInteraction(User user) {
        DetailUserFragment detailFragment = (DetailUserFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (detailFragment != null && detailFragment.isInLayout()) {
            Intent intent = new Intent(getApplicationContext(),
                    DetailUserActivity.class);
            intent.putExtra("user", user);
            detailFragment.setText(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailUserActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }
}

