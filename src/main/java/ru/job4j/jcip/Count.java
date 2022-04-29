package ru.job4j.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс представляет собой многопоточный счетчик,
 * в классе используется библиотека jcip-annotations,
 * данная библиотека позволяет понять где у нас общие ресурсы.
 * Аннотация @ThreadSafe говорит пользователям данного класса,
 * что класс можно использовать в многопоточном режиме и он будет работать правильно.
 * Аннотация @GuardedBy("this") выставляется над общим ресурсом.
 * Аннотация имеет входящий параметр. Он указывает на объект монитора, по которому мы будем синхронизироваться.
 * Работать с этим ресурсом только в критической секции,
 * и синхронизируется по объекту монитора, который указан в аннотации.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
