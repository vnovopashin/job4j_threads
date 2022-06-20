package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 20.06.2022
 */
public class ParallelMergeSortTest {

    @Test
    public void whenSortWithForkJoinPool() {
        int[] arr = new int[]{5, 3, 4, 2, 1, 8, 9, 6, 11, 14, 16, 25, 33};
        int[] res = ParallelMergeSort.sort(arr);
        int[] arrSort = new int[]{1, 2, 3, 4, 5, 6, 8, 9, 11, 14, 16, 25, 33};
        Assert.assertArrayEquals(res, arrSort);
    }
}
