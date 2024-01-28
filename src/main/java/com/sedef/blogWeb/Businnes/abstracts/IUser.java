package com.sedef.blogWeb.Businnes.abstracts;

import Exceptions.NotFoundException;
import com.sedef.blogWeb.Model.Comment;
import com.sedef.blogWeb.Model.User;

import java.util.List;

public interface IUser {
    User getOneUserById(int id) throws NotFoundException;
    List<User> getAllUsers();
    void deleteUserById(int id);
}
