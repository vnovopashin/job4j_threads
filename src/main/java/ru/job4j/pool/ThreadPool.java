package ru.job4j.pool;

import ru.job4j.blockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализует пул потоков,
 * пул потоков позволяет повторно переиспользовать поток для выполнения задач,
 * что позволяет одному потоку выполнять более одной задачи.
 * Пул потоков является альтернативой созданию нового потока для каждой задачи,
 * которую необходимо выполнить.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 04.06.2022
 */
public class ThreadPool {

    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    /**
     * В конструкторе происходит создание потоков,
     * извлечение задачи из очереди и выполнение (task.pool().run()),
     * помещение задачи в threads, а также запуск потоков
     */
    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * Метод добавляет задачу в очередь
     *
     * @param job задача для выполнения
     * @throws InterruptedException бросает исключение в случае прерывания потока
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * Метод вызывает прерывание пула потоков
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
