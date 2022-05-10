package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс представляет собой блокирующий кэш для модели User.
 * Для того чтобы операции были атомарны нам нужен один объект монитора.
 * Объектом монитора будет являться объект класса UserStore.
 * Методы данного класса являются потокобезопасными. Доступ к ним осуществляется последовательно
 * так как используется ключевое слово synchronized.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> userMap = new HashMap<>();

    public synchronized boolean add(User user) {
        return userMap.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized User get(int id) {
        return userMap.get(id);
    }

    public synchronized boolean update(User user) {
        return userMap.replace(user.getId(), user) == null;
    }

    public synchronized boolean delete(User user) {
        return userMap.remove(user.getId(), user);
    }

    /**
     * Метод переводит деньги с одного счета на другой
     *
     * @param fromId id пользователя с которого нужно перевести деньги
     * @param toId   id пользователя на который нужно перевести деньги
     * @param amount сумма перевода
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        User userOne = null;
        User userTwo = null;
        if (userMap.containsKey(fromId) && userMap.containsKey(toId)) {
            userOne = userMap.get(fromId);
            userTwo = userMap.get(toId);
        }
        if (userOne != null && userOne.getAmount() >= amount) {
            userOne.setAmount(userOne.getAmount() - amount);
            userTwo.setAmount(userTwo.getAmount() + amount);
        }
    }
}
