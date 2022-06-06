package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 03.06.2022
 */
public class CacheTest {

    @Test
    public void whenAddThenTrue() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        assertTrue(cache.add(base));
    }

    @Test
    public void whenAdd2Time() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Base baseRepeat = new Base(1, 0);
        assertTrue(cache.add(base));
        assertFalse(cache.add(baseRepeat));
    }

    @Test
    public void whenUpdateOneTimeThenOneVersion() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        base.setName("base");
        cache.update(base);
        Base rsl = cache.getMemory().get(base.getId());
        assertThat(rsl.getVersion(), is(1));
        assertThat(rsl.getName(), is("base"));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateException() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Base base1 = new Base(1, 1);
        cache.add(base);
        cache.update(base1);
    }

    @Test
    public void whenDeleteThenSize0() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.delete(base);
        int size = cache.getMemory().size();
        assertThat(size, is(0));

    }
}
