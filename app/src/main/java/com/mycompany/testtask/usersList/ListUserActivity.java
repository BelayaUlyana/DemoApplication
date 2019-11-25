package com.mycompany.testtask.usersList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

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
            detailFragment.setText(getIntent());
            Log.d("getIntent", getIntent().toString());
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailUserActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE
                && isLarge()) {
            Log.d("ORIENTATION", "_LANDSCAPE && isLarge()   --- ListUserActivity");
        }
    }

    boolean isLarge() {
        return (getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}

