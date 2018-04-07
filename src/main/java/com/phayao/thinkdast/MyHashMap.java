package com.phayao.thinkdast;

import java.util.List;
import java.util.Map;

public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {
    // average number of entries per map before we rehash
    protected static final double FACTOR = 1.0;

    @Override
    public V put(K key, V value) {
        V old = super.put(key, value);

        // check if the number of element per map exceeds the threshold
        if(size() > maps.size() * FACTOR) {
            rehash();
        }
        return old;
    }

    /**
     * Double number of maps and rehashes the existing entries.
     */
    protected void rehash() {
        // save the existing entries
        List<MyLinearMap<K, V>> oldMaps = maps;

        // make more maps
        int k = maps.size() * 2;
        makeMaps(k);

        //System.out.println("Rehashing, n is now " + k);

        // put the entries into the new map
        for(MyLinearMap<K, V> map: oldMaps) {
            for(Map.Entry<K, V> entry: map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Integer> map = new MyHashMap<String, Integer>();
        for(int i = 0; i < 10; i++) {
            map.put(new Integer(i).toString(), i);
        }
        Integer value = map.get("3");
        System.out.println(value);
    }
}
