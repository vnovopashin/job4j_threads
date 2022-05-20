package ru.job4j.barrier;

/**
 * Демонстрируется работа класс CountBarrier, в конструктор передается значение
 * переменной total (содержит количество вызовов метода count()), нити которые выполняют метод await(),
 * могут начать работу если количество вызовов метода count() будет больше или равно значению total.
 * В данном случае total равно 2, вызов метода count() равен также 2
 * (значение total и count() удовлетворяют условию и нить пробуждается), соответственно программа завершается,
 * если увеличить значение в конструкторе например до 3 или уменьшить количество вызовов метода count(),
 * программа перейдет в состояние ожидания.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class CountBarrierRunner {
    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(2);

        Thread one = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            barrier.await();
        }, "One thread");

        Thread two = new Thread(() -> {
            barrier.count();
            barrier.count();
            System.out.println(Thread.currentThread().getName() + " started");
        }, "Two thread");

        one.start();
        two.start();
    }
}
