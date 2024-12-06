package com.poc.redis_proof_of_concept.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.redis_proof_of_concept.dto.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    private Jedis jedis = new Jedis();
    @Autowired
    private RedisTemplate<String, User> template;

    public UserService() throws Exception {
        users.add(
            User.builder()
                .id(1)
                .name("Test User")
                .email("first@test.com")
                .contactNumber("123456")
                .status(true)
                .build());
        users.add(
            User.builder()
                .id(2)
                .name("second test user")
                .email("second@test.com")
                .contactNumber("123456")
                .status(true)
                .build());
        users.add(
            User.builder()
                .id(3)
                .name("Third test user")
                .email("third@test.com")
                .contactNumber("123456")
                .status(true)
                .build());
    }

    public List<User> getAllUsers() throws JsonProcessingException {
        String hget = jedis.hget("users", "user-data");
        if (hget != null) {
            users.addAll(mapper.readerForListOf(User.class).readValue(hget));
        }
        return users;
    }

    public User setUser(User user, int id) throws InterruptedException, JsonProcessingException {
        Thread.sleep(1000);
        user.setStatus(true);
        this.users.add(user);
        List<User> users = new ArrayList<>();
        String hget = jedis.hget("users", "user-data");
        if (hget != null) {
            users.addAll(mapper.readerForListOf(User.class).readValue(hget));
        }
        System.out.println("FROM set" + hget);
        users.add(user);
        jedis.hset("users", "user-data", mapper.writeValueAsString(users));
        return user;
    }

    public User getUserById(int id) throws Exception {
        String hget = jedis.hget("users", "user-data");
        System.out.println("From get" + hget);
        List<User> users = mapper.readerForListOf(User.class).readValue(hget);
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void evictData(int id) throws JsonProcessingException {
        String hget = jedis.hget("users", "user-data");
        System.out.println("From get" + hget);
        List<User> cachedUsers = mapper.readerForListOf(User.class).readValue(hget);
        List<User> newCachedUsers = new ArrayList<>();
        for (User user : cachedUsers) {
            if (user.getId() != id) {
                newCachedUsers.add(user);
            }
        }
        jedis.hset("users", "user-data", mapper.writeValueAsString(newCachedUsers));
    }
}
