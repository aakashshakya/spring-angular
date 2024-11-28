package com.poc.redis_proof_of_concept.service;

import com.poc.redis_proof_of_concept.dto.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
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

    public User setUser(User user, int id) throws InterruptedException {
        Thread.sleep(1000);
        template.opsForSet().add("users::" + id, user);
        user.setStatus(true);
        users.add(user);
        return user;
    }

    public User getUserById(int id) throws Exception {
        User user;
        Set<User> redisUsers = template.opsForSet().members("users::" + id);
        if (redisUsers != null && !redisUsers.isEmpty()) {
            user = redisUsers.stream().findFirst().orElse(null);
            Jedis jedis = new Jedis();
            jedis.hset("user::" + id, "user-data", user.toString());
            return user;
        }
        return users.stream().filter(user1 -> user1.getId() == id).findFirst().orElseThrow(Exception::new);
    }

    public void evictData(int id) {
        template.opsForSet().remove("users::" + id, users.stream().filter(user1 -> user1.getId() == id).findFirst().get());
    }
}
