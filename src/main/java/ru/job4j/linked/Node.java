package ru.job4j.linked;

/**
 * Класс описывает узел односвязного списка.
 * Данный класс является immutable, так как его состояние нельзя изменить после создания.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
