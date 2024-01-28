package com.sedef.blogWeb.Businnes.concretes;
import Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.abstracts.IUser;
import com.sedef.blogWeb.Model.User;
import com.sedef.blogWeb.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUser {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getOneUserById(int id) throws NotFoundException {
        return userRepository.findById((long) id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById((long) id);
    }
}
