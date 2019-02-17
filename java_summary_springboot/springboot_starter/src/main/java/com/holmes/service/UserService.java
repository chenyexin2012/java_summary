package com.holmes.service;

import com.holmes.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    private static Map<Integer, User> userMap = new ConcurrentHashMap<>(10);

    private static AtomicInteger idGenerator = new AtomicInteger(10);

    static {
        for (int i = 0; i < 10; i++) {
            userMap.put(i, new User(i, "user" + i, i + 20));
        }
    }

    public List<User> getAll() {

        Collection<User> allUsers = userMap.values();
        return new ArrayList<>(allUsers);
    }

    public User getUser(Integer id) {
        return userMap.get(id);
    }

    public User addUser(User user) {

        Integer id = idGenerator.incrementAndGet();
        user.setId(id);

        userMap.put(id, user);

        return user;
    }

    public void delete(Integer id) {
        userMap.remove(id);
    }

    public void update(User user) {
        userMap.put(user.getId(), user);
    }

}
