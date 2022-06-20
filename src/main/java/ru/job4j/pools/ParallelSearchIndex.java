package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Класс реализует параллельный поиск объекта в массиве и возвращает его индекс
 * (в целях оптимизации если размер массива не больше 10, используется обычный линейный поиск),
 * для этого используется фреймворк ForkJoinPool,
 * он позволяет разделить задачу на более мелкие подзадачи (пока задача не будет решена),
 * а затем объединить в итоговый результат.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 15.06.2022
 */
public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {

    private final T[] arr;
    private final T target;
    private final int start;
    private final int end;

    public ParallelSearchIndex(T[] arr, int start, int end, T target) {
        this.arr = arr;
        this.target = target;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int result = -1;
        if (end - start <= 10) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(target)) {
                    result = i;
                    break;
                }
            }
            return result;
        }
        int mid = (end + start) / 2;
        ParallelSearchIndex<T> leftSearch = new ParallelSearchIndex<>(arr, start, mid, target);
        ParallelSearchIndex<T> rightSearch = new ParallelSearchIndex<>(arr, mid + 1, end, target);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public static <T> Integer search(T[] arr, T target) {
        ForkJoinPool fjp = new ForkJoinPool();
        return fjp.invoke(new ParallelSearchIndex<>(arr, 0, arr.length - 1, target));
    }
}
