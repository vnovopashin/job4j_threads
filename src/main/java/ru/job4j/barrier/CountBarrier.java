package ru.job4j.barrier;

/**
 * Класс блокирует выполнение программы по условию счетчика
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Метод изменяет состояние поля count(увеличивает счетчик).
     * И пробуждает нити которые ожидали изменения состояния.
     */
    public void count() {
        synchronized (monitor) {
            ++count;
            monitor.notifyAll();
        }
    }

    /**
     * Метод переводит нить в состояние ожидания.
     * Пока поле count не достигло или превысило значения total.
     */
    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
