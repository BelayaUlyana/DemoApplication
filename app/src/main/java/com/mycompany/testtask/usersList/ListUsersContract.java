package com.mycompany.testtask.usersList;

import com.mycompany.testtask.POJO.User;
import java.util.List;

public interface ListUsersContract {
    void showInfo(List<User> userList);
    void showError(String error);
}
