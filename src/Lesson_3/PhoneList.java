package Lesson_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneList<K, V> {
    private Map<K, Set<V>> db = new HashMap<>();

    public void add(K key, V phone) {
        final Set<V> phones = getItemFromDBByKey(key, 1);
        phones.add(phone);
        db.put(key, phones);
    }

    public Set<V> get(K key) {
        return getItemFromDBByKey(key, 0);
    }

    private Set<V> getItemFromDBByKey(K key, int capacity) {
        return db.getOrDefault(key, new HashSet<>(capacity));
    }
}
