package ru.job4j.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Класс реализует bounded blocking queue.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int queueMaxSize;

    public SimpleBlockingQueue(int queueMaxSize) {
        this.queueMaxSize = queueMaxSize;
    }

    public int size() {
        return queue.size();
    }

    /**
     * Метод позволяет положить в очередь элемент value,
     * если очередь заполнена то поток ставится в ожидание (засыпает),
     * иначе поток пробуждается и элемент добавляется в очередь
     *
     * @param value значение для помещения в очередь
     */
    public synchronized void offer(T value) {
        while (queue.size() == queueMaxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(value);
        notify();
    }

    /**
     * Метод извлекает элемент из очереди, если очередь "пустая", поток ставится в ожидание (засыпает).
     *
     * @return удаляет элемент из очереди и возвращает его.
     */
    public synchronized T poll() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T rsl = queue.poll();
        notify();
        return rsl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleBlockingQueue<?> that = (SimpleBlockingQueue<?>) o;
        return queueMaxSize == that.queueMaxSize && Objects.equals(queue, that.queue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queue, queueMaxSize);
    }
}
