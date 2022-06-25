package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;

/**
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 21.06.2022
 */
public class RolColSumTest {
    @Test
    public void whenSumSync() {
        RolColSum.Sums[] rolColSum = RolColSum.sum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        Assert.assertThat(rolColSum[0].getRowSum(), is(6));
        Assert.assertThat(rolColSum[1].getRowSum(), is(15));
        Assert.assertThat(rolColSum[2].getRowSum(), is(24));
        Assert.assertThat(rolColSum[0].getColSum(), is(12));
        Assert.assertThat(rolColSum[1].getColSum(), is(15));
        Assert.assertThat(rolColSum[2].getColSum(), is(18));
    }

    @Test
    public void whenSumAsync() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] rolColSum = RolColSum.asyncSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        Assert.assertThat(rolColSum[0].getRowSum(), is(6));
        Assert.assertThat(rolColSum[1].getRowSum(), is(15));
        Assert.assertThat(rolColSum[2].getRowSum(), is(24));
        Assert.assertThat(rolColSum[0].getColSum(), is(12));
        Assert.assertThat(rolColSum[1].getColSum(), is(15));
        Assert.assertThat(rolColSum[2].getColSum(), is(18));
    }
}
