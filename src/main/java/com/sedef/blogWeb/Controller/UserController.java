package com.sedef.blogWeb.Controller;

import Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.concretes.UserService;
import com.sedef.blogWeb.Model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
 UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getOne/{id}")
    public User getOneUserById(@PathVariable int id) throws NotFoundException {
        return userService.getOneUserById(id);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }
}
