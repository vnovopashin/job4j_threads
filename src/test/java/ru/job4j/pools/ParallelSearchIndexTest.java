package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * В классе приведены тесты для наиболее типичных случаев
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 20.06.2022
 */
public class ParallelSearchIndexTest {
    @Test
    public void whenLessThanTen() {
        Integer[] arr = new Integer[]{5, 4, 3, 1, 2};
        Integer res = ParallelSearchIndex.search(arr, 3);
        Assert.assertThat(res, is(2));
    }

    @Test
    public void whenMoreThanTen() {
        Integer[] arr = new Integer[]{5, 4, 3, 1, 2, 6, 8, 9, 7, 10, 11, 12, 99};
        Integer res = ParallelSearchIndex.search(arr, 9);
        Assert.assertThat(res, is(7));
    }

    @Test
    public void whenString() {
        String[] arr = new String[]{"5", "4", "3", "1", "2", "6", "8", "9", "7", "10", "11", "12", "99"};
        Integer res = ParallelSearchIndex.search(arr, "9");
        Assert.assertThat(res, is(7));
    }

    @Test
    public void whenNotFound() {
        Integer[] arr = new Integer[]{5, 4, 3, 1, 2, 6, 8, 9, 7, 10, 11, 12};
        Integer res = ParallelSearchIndex.search(arr, 99);
        Assert.assertThat(res, is(-1));
    }
}
