package ru.job4j.concurrent;

/**
 * Класс создает две нити, каждая нить выводит свое состояние и имя на консоль.
 * Нити могут иметь следующие состояния:
 * NEW - нить создана, но не запущена.
 * RUNNABLE - нить запущена и выполняется.
 * BLOCKED - нить заблокирована.
 * WAITING - нить ожидает уведомления.
 * TIMED_WAITING - нить ожидает уведомление в течении определенного периода.
 * TERMINATED - нить завершила работу.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                }, "I am thread number one"
        );
        Thread second = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                }, "I am thread number two"
        );
        System.out.println(first.getName() + " - " + first.getState());
        System.out.println(second.getName() + " - " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " - " + first.getState());
            System.out.println(second.getName() + " - " + second.getState());
        }

        System.out.println(first.getName() + " - " + first.getState());
        System.out.println(second.getName() + " - " + second.getState());
        System.out.println(Thread.currentThread().getName());
        System.out.println("work completed");
    }
}
