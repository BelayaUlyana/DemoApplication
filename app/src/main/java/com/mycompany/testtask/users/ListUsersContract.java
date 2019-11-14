package com.mycompany.testtask.users;

import com.mycompany.testtask.POJO.User;
import java.util.List;

public interface ListUsersContract {
    void showInfo(List<User> userList);
    void showError(String error);
}
