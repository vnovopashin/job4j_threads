package ru.job4j.jcip;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Тест состоит из двух нитей. Каждая нить будет дергать счетчик и увеличивать его значение на единицу.
 * В конце теста мы проверим, что наш счетчик увеличился на нужное количество раз.
 * Как мы видим все тестирование с нитями сводиться к тестированию последовательных операций.
 * Этого мы достигаем за счет использования метода join.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class CountTest {
    /**
     * Класс описывает нить со счетчиком.
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    /**
     * В данном методе создается счетчик(count), две нити(first, second),
     * затем они запускаются методом start, метод join заставляет дождаться
     * главную нить выполнение наших нитей и проверяем результат.
     *
     * @throws InterruptedException бросает данное исключение.
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }

}
