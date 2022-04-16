package ru.job4j.concurrent;

/**
 * Класс демонстрирует пример прерывания нити, метод interrupt() выставляет флаг прерывания,
 * но ни каких действий не совершает, для проверки флага воспользуемся методом isInterrupted().
 * Метод Thread.interrupt() не выставляет флаг прерывания, если нить находится в режиме WAIT, JOIN.
 * В этом случае методы sleep(), join(), wait() выкинут исключение.
 * Поэтому нужно дополнительно проставить флаг прерывания.
 * В блоке catch нужно дополнительно вызывать прерывание нити для того чтобы прерывание действительно произошло.
 * Эта схема является шаблоном. Запомните, если используются методы sleep(), join() или wait(),
 * то нужно в блоке catch вызвать прерывание.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] process = {"\\", "|", "/"};
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (count == process.length) {
                count = 0;
            }
            System.out.print("\rload: " + process[count++]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
