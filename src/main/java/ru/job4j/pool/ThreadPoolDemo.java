package ru.job4j.pool;

/**
 * Класс демонстрирует работу пула потоков
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 04.06.2022
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 4; i++) {
            Runnable job = new Job("Job-" + i);
            try {
                threadPool.work(job);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        threadPool.shutdown();
    }
}
