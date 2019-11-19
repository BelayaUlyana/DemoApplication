package com.mycompany.testtask.usersList;

import android.content.Context;
import android.util.Log;

import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.usersList.Database.DatabaseCreator;
import com.mycompany.testtask.usersList.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ListUsersPresenter {

    private ListUsersContract mainContract;
    private Context context;


    ListUsersPresenter(ListUsersContract mainContract, Context context) {
        this.mainContract = mainContract;
        this.context = context;
    }

    void getUserList() {
        Call<List<User>> call = RetrofitClient.getApiService().getAllPairs();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mainContract.showInfo(response.body());
                DatabaseCreator.getDatabase(context).getUserDao().insertAll(response.body());
                Log.d("TAG", "Response = " + response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                mainContract.showError(t.getMessage());
            }
        });

    }

    void getUserListDB() {
        List<User> userList = DatabaseCreator.getDatabase(context).getUserDao().getAll();
        Log.d("TAG", "getUserListDB = " + userList);
        mainContract.showInfo(userList);
    }
}
