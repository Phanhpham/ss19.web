package com.data.service;

import com.data.model.User;
import com.data.repository.UserRepo;
import com.data.repository.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Override
    public List<User> getAllUser() {
        return userRepo.getAllUser();
    }

    @Override
    public List<User> getUsersByPage(int pageNo, int pageSize) {
        return userRepo.getUsersByPage(pageNo,pageSize);
    }

    @Override
    public long countTotalUsers() {
        return userRepo.countTotalUsers();
    }

    @Override
    public void blockUserById(int id) {
        userRepo.blockUserById(id);
    }

    @Override
    public void unblockUserById(int id) {
         userRepo.unblockUserById(id);
    }
}
