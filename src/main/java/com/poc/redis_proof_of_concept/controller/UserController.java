package com.poc.redis_proof_of_concept.controller;

import com.poc.redis_proof_of_concept.dto.User;
import com.poc.redis_proof_of_concept.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void setUser(@RequestBody User user) throws InterruptedException {
        userService.setUser(user, user.getId());
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }

    @GetMapping("delete/{id}")
    public void evictData(@PathVariable int id) {
        userService.evictData(id);
    }
}
