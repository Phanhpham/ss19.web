package com.data.repository;


import com.data.model.User;

import java.util.List;

public interface UserRepo {
    List<User> getAllUser();
    public List<User> getUsersByPage(int pageNo, int pageSize);
    public long countTotalUsers();
    void blockUserById(int id);
    void unblockUserById(int id);
}
