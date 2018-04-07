package com.phayao.thinkdast;

import java.util.Map;

public class MyFixedHashMap<K, V> extends MyHashMap<K, V> implements Map<K, V> {
    private int size = 0;

    @Override
    public void clear() {
        super.clear();
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        MyLinearMap<K, V> map = chooseMap(key);
        size -= map.size();
        V old = map.put(key, value);
        size += map.size();

        if(size() > maps.size() * FACTOR) {
            size = 0;
            rehash();
        }
        return old;
    }

    @Override
    public V remove(Object key) {
        MyLinearMap<K, V> map = chooseMap(key);
        size -= map.size();
        V old = map.remove(key);
        size += map.size();
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Integer> map = new MyFixedHashMap<String, Integer>();
        for(int i = 0; i < 10; i++) {
            map.put(new Integer(i).toString(), i);
        }
        Integer value = map.get("3");
        System.out.println(value);
    }

}
