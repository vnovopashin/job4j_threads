package ru.job4j.cas;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 02.06.2022
 */
public class CASCountTest {

    @Test
    public void when4IncrementThen4Get() throws InterruptedException {
        CASCount count = new CASCount();
        Thread th = new Thread(() -> {
            count.increment();
            count.increment();
        });

        Thread th2 = new Thread(() -> {
            count.increment();
            count.increment();
        });
        th.start();
        th2.start();
        th.join();
        th2.join();
        assertThat(count.get(), is(4));
    }
}
