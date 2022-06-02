package ru.job4j.blockingqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

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
                try {
                    simpleBlockingQueue.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    simpleBlockingQueue.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(simpleBlockingQueue.size(), is(0));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(i -> {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}
