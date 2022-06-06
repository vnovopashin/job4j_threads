package ru.job4j.cache;

/**
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 03.06.2022
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
