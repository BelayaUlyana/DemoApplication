package com.mycompany.testtask.usersList.Network;

import com.mycompany.testtask.POJO.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<User>> getAllPairs();
}
