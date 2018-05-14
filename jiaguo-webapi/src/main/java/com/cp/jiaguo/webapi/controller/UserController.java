package com.cp.jiaguo.webapi.controller;

import java.util.List;

import com.cp.jiaguo.webapi.model.User;

import com.cp.jiaguo.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Integer id) {
        return this.userService.getById(id);
    }

    @PostMapping("/")
    public void insert(@RequestBody User user) {
        this.userService.insert(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody User user) {
        User u = this.userService.getById(id);
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        this.userService.update(u);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.userService.deleteById(id);
    }

    @GetMapping("/insert")
    public User testInsert(@RequestParam("name") String name, @RequestParam(name = "email", required = false) String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userService.insert(user);
        return user;
    }

    @GetMapping("/update/{id}")
    public User testUpdate(@PathVariable("id") Integer id) {
        User user = userService.getById(id);

        // switch name and email
        String name = user.getName();
        user.setName(user.getEmail());
        user.setEmail(name);
        userService.update(user);
        return user;
    }

    @GetMapping("/delete/{id}")
    public List<User> testDelete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return getAll();
    }
}
