package ru.job4j.executorservice;

/**
 * Класс описывает модель данных типа User
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 12.06.2022
 */
public class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
