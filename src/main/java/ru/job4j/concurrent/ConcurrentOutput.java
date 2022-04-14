package ru.job4j.concurrent;

/**
 * Класс демонстрирует создание нитей исполнения, с использованием библиотечного класса Thread
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
