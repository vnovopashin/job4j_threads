package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Класс реализует неблокирующий счетчик
 * с использованием CAS(compare and swap) операции
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 02.06.2022
 */

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int old;
        int increment;
        do {
            old = count.get();
            increment = old + 1;
        } while (!count.compareAndSet(old, increment));
    }

    public int get() {
        return count.get();
    }
}
