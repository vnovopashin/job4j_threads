package ru.job4j.concurrent;

/**
 * Класс представляет собой singleton с двойной проверкой. Двойная проверка позволяет избежать ситуации,
 * когда объект сконструирован не полностью. Т.е. ссылка (inst) уже не null, а объект еще не сконструирован,
 * и другой поток может уже начать использовать данную ссылку.
 * <p>
 * Добавление ключевого слова volatile позволяет не кэшировать переменную,
 * таким образом иметь всегда актуальное значение переменной.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }
}
