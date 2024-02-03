package com.sedef.blogWeb.Controller;

import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.concretes.UserService;
import com.sedef.blogWeb.Model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth") //openapi deki name ile aynı olmalı
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
