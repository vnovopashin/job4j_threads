package ru.job4j.blockingqueue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class SimpleBlockingQueueTest {
    @Test
    public void whenSimpleBlockingQueueEmpty() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                simpleBlockingQueue.offer(i);
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                simpleBlockingQueue.poll();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(simpleBlockingQueue.size(), is(0));
    }
}
