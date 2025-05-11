---
id: valid-anagram
aliases: []
tags: []
layout: default
title: Valid Anagram
---

## Solution #1, T:O(n) S:O(n)

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        var map1 = createFreqMap(s);
        var map2 = createFreqMap(t);

        // make sure it doesn't contain less or extra characters
        if (map1.size() != map2.size()) {
            return false;
        }

        for (var entry : map1.entrySet()) {
            if (map2.getOrDefault(entry.getKey(), -1) != entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private Map<Character, Integer> createFreqMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }
        return map;
    }
}
```

## Solution #2, T:O(n log n) S:O(n)

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        return Arrays.equals(s1, s2);
    }
}
```

## Solution #3, T:O(n) S:O(n)

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        // create freq of string `s`
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
            map.merge(t.charAt(i), -1, Integer::sum);
        }

        // if every freq is 0, then alright otherwise not anagram
        for (var entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }
}
```
