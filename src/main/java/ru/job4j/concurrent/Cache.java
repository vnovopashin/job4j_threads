package ru.job4j.concurrent;

/**
 * Класс демонстрирует пример работы с ключевым словом synchronized (чтобы добиться атомарности не атомарных операций),
 * которое позволяет синхронизировать метод instOf(),
 * таким образом если две нити пробуют выполнить один и тот же синхронизированный метод,
 * то одна из нитей переходит в режим блокировки до тех пор, пока первая нить не закончит работу с этим методом.
 * Синхронизация делает параллельную программу последовательной.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public final class Cache {
    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
