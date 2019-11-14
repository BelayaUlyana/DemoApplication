package com.mycompany.testtask.usersList;

import android.util.Log;

import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ListUsersPresenter {

    private ListUsersContract mainContract;

    ListUsersPresenter(ListUsersContract mainContract) {
        this.mainContract = mainContract;
    }


    void getUserList() {
        Call<List<User>> call = RetrofitClient.getApiService().getAllPairs();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mainContract.showInfo(response.body());
                System.out.println("Response = " + response.body());
                Log.d("TAG", "Response = " + response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                mainContract.showError(t.getMessage());
            }
        });

    }
}
