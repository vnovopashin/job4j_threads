package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс реализует консольную программу - аналог wget.
 * Программа скачивает файл из сети с ограничением скорости скачивания.
 * Для скачивания необходимо указать ссылку на скачиваемый файл, скорость с которой будет скачиваться файл
 * и где будет храниться скаченный файл.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String file;

    public Wget(String url, int speed, String file) {
        this.url = url;
        this.speed = speed;
        this.file = file;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long end = System.currentTimeMillis();
                long resultTime = end - start;
                long expTime = bytesRead / speed;
                try {
                    if (resultTime < expTime) {
                        Thread.sleep(expTime - resultTime);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                start = System.currentTimeMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];
        Thread wget = new Thread(new Wget(url, speed, file));
        wget.start();
        wget.join();
    }
}
