package ru.job4j.concurrent;

/**
 * Класс симулирует процесс загрузки файла, используя статический метод sleep(),
 * который позволяет приостановить нить на заданное в параметре количество миллисекунд.
 * Метод sleep() переводит нить в состояние TIMED_WAITING.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 0; index <= 100; index++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + index + "%");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
