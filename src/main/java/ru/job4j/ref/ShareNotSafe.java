package ru.job4j.ref;

import java.util.List;

/**
 * Класс демонстрирует многопоточную работу с локальной копией,
 * что позволяет избавиться от общих ресурсов.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> user.setName("rename")
        );
        first.start();
        first.join();
        System.out.println(cache.findById(1).getName());
        List<User> userList = cache.findAll();
        for (User u : userList) {
            System.out.println(u.getName());
        }
    }
}
