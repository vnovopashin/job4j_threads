package ru.job4j.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * В классе реализованы методы для работы в многопоточной среде,
 * для этого используется копия общего ресурса (общий ресурс это модель User),
 * работа в методах ведется не напрямую, а через метод User.of(),
 * который позволяет получить копию модели данных User.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */

public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        for (User value : users.values()) {
            userList.add(User.of(value.getName()));
        }
        return userList;
    }
}
