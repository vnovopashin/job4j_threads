package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс представляет неблокирующий кэш,
 * позволяющий работать с данными сразу из памяти в многопоточном режиме,
 * что дает значительный прирост скорости.
 * Для решения данной задачи используются CAS методы (работают атомарно) putIfAbsent(), computeIfPresent().
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 * @date 03.06.2022
 */
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public synchronized Map<Integer, Base> getMemory() {
        return memory;
    }

    /**
     * Метод атомарно добавляет модель типа Base в кэш
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * Метод атомарно обновляет модель данных, если version у модели и в кэше одинаковые,
     * то происходит обновление, иначе выбрасывается исключение OptimisticException.
     */
    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, val) -> {
            if (model.getVersion() != val.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base updateBase = new Base(model.getId(), model.getVersion() + 1);
            updateBase.setName(model.getName());
            return updateBase;
        }) != null;
    }

    /**
     * Метод атомарно удаляет модель данных из кэша
     */
    public void delete(Base model) {
        memory.remove(model.getId());
    }
}
