package com.intranet_F5.Controller;

import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public UserModel getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public String addNewUser(@RequestBody UserModel newUser){
            return userService.addNewUser(newUser);
    }

    @DeleteMapping(path="/delete/{id}")
    public String deleteUSer(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @PutMapping(path="upgrade/{id}")
    public UserModel upgradeUser(@PathVariable long id, @RequestBody UserModel newUser){
        return userService.upgradeUser(id, newUser);
    }
}
