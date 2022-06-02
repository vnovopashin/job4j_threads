package ru.job4j.buffer;

import ru.job4j.blockingqueue.SimpleBlockingQueue;

/**
 * Класс демонстрирует правильную работу остановки потока с использованием InterruptedException
 * Как только нить производитель закончит свою работу, при помощи consumer.interrupt(),
 * нить потребитель будет остановлена.
 * Если в ходе выполнения программы выбрасывается InterruptedException,
 * то флаг остановки сбрасывается, поэтому мы в catch заново прерываем нить
 * при помощи Thread.currentThread().interrupt().
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}
