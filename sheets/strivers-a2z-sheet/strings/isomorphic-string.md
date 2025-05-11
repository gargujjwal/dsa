---
id: isomorphic-string
aliases: []
tags: []
layout: default
title: Isomorphic String
---

## Optimal Solution #1

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            // check if we have a mapping with value as b
            if (map.containsValue(b)) {
                if (!map.containsKey(a) || map.get(a) != b) {
                    return false;
                }
            }

            // check if we have a mapping a -> b
            if (map.containsKey(a)) {
                if (map.get(a) != b) {
                    return false;
                }
            } else {
                map.put(a, b);
            }
        }

        return true;
    }
}
```

## Optimal Solution #2

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if (map.containsKey(a)) {
                if (map.get(a) != b) {
                    return false;
                }
            } else if (map.containsValue(b)) {
                return false;
            }

            map.put(a, b);
        }

        return true;
    }
}
```
