package ru.job4j.storage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * В тестовом классе мы вызываем метод transfer (в двух потоках)
 * соответственно он будет вызван по разу в каждом потоке.
 * Поэтому у user с id = 1, будет на счету 0, а у user с id = 2, будет 300.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class UserStorageTest {
    private class ThreadUserStorage extends Thread {
        private final UserStorage userStorage;

        private ThreadUserStorage(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            User user = new User(1, 100);
            User user2 = new User(2, 200);
            userStorage.add(user);
            userStorage.add(user2);
            userStorage.transfer(1, 2, 50);
        }
    }

    @Test
    public void whenExecute2Thread() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();
        Thread first = new ThreadUserStorage(userStorage);
        Thread second = new ThreadUserStorage(userStorage);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(userStorage.get(1).getAmount(), is(0));
        assertThat(userStorage.get(2).getAmount(), is(300));
    }
}
