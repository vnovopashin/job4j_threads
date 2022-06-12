package ru.job4j.pool;

/**
 * Класс реализует задачу, для выполнения в пуле потоков
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 04.06.2022
 */
public class Job implements Runnable {
    private final String name;

    public Job(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Start Thread: " + name);
        for (int index = 0; index <= 100; index++) {
            System.out.print("\rLoading : " + index + "%");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Ended Thread: " + name);
    }
}
