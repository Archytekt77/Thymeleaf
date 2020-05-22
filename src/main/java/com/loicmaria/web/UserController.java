package com.loicmaria.web;

import com.loicmaria.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.loicmaria.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/get")
    public String getUsers(Model model){
        model.addAttribute("users", userService.getter());
        return "user/getUser";
    }

    @GetMapping("/details")
    public String getUserById(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("user", userService.get(id));
        return "user/detailsUser";
    }

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "user/createUser";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute User user, Model model){
        userService.add(user);
        model.addAttribute("users", userService.getter());
        return "home";
    }

    @GetMapping("/edition")
    public String editionUser(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("user", userService.get(id));
        model.addAttribute("id", id);
        return "user/editionUser";
    }

    @PostMapping("/edition/{id}")
    public String updateUser(@PathVariable(value = "id") int id, Model model, User user){
        userService.update(user);
        model.addAttribute("user", userService.get(id));
        return "user/detailsUser";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("user", userService.get(id));
        userService.delete(id);
        return "user/deleteUser";
    }
}