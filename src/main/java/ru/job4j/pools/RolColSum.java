package ru.job4j.pools;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Класс считает суммы по строкам и столбцам квадратной матрицы.
 * В классе реализованно два метода sum и asyncSum, вариант последовательного и асинхронного выполнения.
 * CompletableFuture используется для асинхронного программирования.
 * Асинхронное программирование — это средство написания неблокирующего кода путём выполнения задачи в отдельном,
 * отличном от главного, потоке, а также уведомление главного потока о ходе выполнения, завершении или сбое.
 * Таким образом, основной поток не блокируется и не ждёт завершения задачи,
 * а значит может параллельно выполнять и другие задания.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 21.06.2022
 */
public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    /**
     * Метод считает сумму по строкам и столбцам квадратной матрицы, последовательно
     *
     * @param matrix матрица столбцы и строки которой необходимо суммировать
     * @return возвращает массив состоящий из сумм каждой строки и столбца
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int totalRowSum = 0;
            int totalColSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                totalRowSum += matrix[i][j];
                totalColSum += matrix[j][i];
            }
            Sums sum = new Sums();
            sum.setRowSum(totalRowSum);
            sum.setColSum(totalColSum);
            sums[i] = sum;
        }
        return sums;
    }

    /**
     * Метод считает сумму по строкам и столбцам квадратной матрицы, асинхронно.
     *
     * @param matrix матрица, столбцы и строки которой необходимо суммировать
     * @return возвращает массив состоящий из сумм каждой строки и столбца
     * @throws ExecutionException   бросает исключение в случае ошибки
     * @throws InterruptedException бросает исключение в случае ошибки
     */
    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            Sums s = new Sums();
            futures.put(i, getTask(matrix, s, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    /**
     * Метод запускает асинхронную задачу по вычислению суммы элементов каждой строки и столбца
     * и присваивает результат в поля объекта Sums
     *
     * @param matrix матрица, строки и столбцы которой необходимо суммировать
     * @param sums   объект в поле которого будет помещен результат суммирования
     * @param index  индекс строки и столбца
     * @return инициализирует поля rowSum, объекта типа Sums
     * и возвращает CompletableFuture<Sums> значение которого можно получить методом get()
     */
    public static CompletableFuture<Sums> getTask(int[][] matrix, Sums sums, int index) {
        return CompletableFuture.supplyAsync(() -> {
            int totalRowSum = 0;
            int totalColSum = 0;
            for (int i = 0; i < matrix.length; i++) {
                totalRowSum += matrix[index][i];
                totalColSum += matrix[i][index];
            }
            sums.setRowSum(totalRowSum);
            sums.setColSum(totalColSum);
            return sums;
        });
    }
}
