package com.jihan.algorithm;

import java.util.HashMap;

/**
 * Created by Jihan on 2019/7/27
 */
public class HashNewStructure {

    public static class RandomPool {
        HashMap<String, Integer> keyMap = new HashMap<>();
        HashMap<Integer, String> indexMap = new HashMap<>();
        int size = 0;

        public void addStr(String key) {
            keyMap.put(key, size);
            indexMap.put(size, key);
            size++;
        }

        public boolean removeStr(String key) {
            if (!keyMap.containsKey(key)) {
                return false;
            }
            //把indexMap中被删除的值替换为第size个的值
            indexMap.put(keyMap.get(key), indexMap.get(size));
            //把indexMap第size个的新位置更新到keyMap
            keyMap.put(indexMap.get(size), keyMap.get(key));
            keyMap.remove(key);
            indexMap.remove(size);
            size--;
            return true;
        }

        public String getRandom() {
            if (size == 0) {
                return null;
            }
            int random = (int) (Math.random() * size);
            return indexMap.get(random);
        }
    }
}
