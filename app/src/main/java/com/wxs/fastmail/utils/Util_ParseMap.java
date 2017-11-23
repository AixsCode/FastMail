package com.wxs.fastmail.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util_ParseMap {

    public static Map<String, List<String>> getKeys(Map<String, Integer> map) {
        List<String> keys = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            keys.add(entry.getKey());
        }

        if (keys.size() != 0) {
            Map<String, List<String>> map2 = new HashMap<>();
            map2.put("keys", keys);
            return map2;
        }
        return null;
    }

    public static Map<String, List<Integer>> getValues(Map<String, Integer> map) {
        List<Integer> values = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            values.add(entry.getValue());
        }

        if (values.size() != 0) {
            Map<String, List<Integer>> map2 = new HashMap<>();
            map2.put("values", values);
            return map2;
        }
        return null;
    }

    public static String getKeyOrValue(Map<String, Integer> map, String key, int value) {
        Map<String, List<String>> keyMap = new HashMap<>();
        keyMap = Util_ParseMap.getKeys(map);
        Map<String, List<Integer>> valueMap = new HashMap<>();
        valueMap = Util_ParseMap.getValues(map);

        if (keyMap != null && valueMap != null) {
            List<String> keyList = keyMap.get("keys");
            List<Integer> valueList = valueMap.get("values");

            int i = 0;
            // getKey
            if (value != 0) {
                for (; i < valueList.size(); i++) {
                    if (value == valueList.get(i)) {
                        break;
                    }
                }

                if (i < valueList.size()) {
                    return keyList.get(i);
                }
            }
            // getValue
            else if (key != null) {
                for (; i < keyList.size(); i++) {
                    if (key.equals(keyList.get(i))) {
                        break;
                    }
                }

                if (i < keyList.size()) {
                    return String.valueOf(valueList.get(i));
                }
            }
        }
        return null;
    }
}
