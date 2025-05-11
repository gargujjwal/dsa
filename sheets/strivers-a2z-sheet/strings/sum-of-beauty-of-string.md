---
id: sum-of-beauty-of-string
aliases: []
tags: []
layout: default
title: Sum of Beauty of String
---

## Brute Solution

```java
class Solution {
    public int beautySum(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j < s.length(); j++) {
                ans += beautyOfString(s, i, j);
            }
        }

        return ans;
    }

    private int beautyOfString(String s, int start, int end) {
        Map<Character, Integer> freq = new HashMap<>();

        // Count frequencies
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int count : freq.values()) {
            min = Math.min(min, count);
            max = Math.max(max, count);
        }

        return max - min;
    }
}
```

## Optimal Solution

```java
class Solution {
    public int beautySum(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int[] map = new int[26];
            for (int j = i; j < s.length(); j++) {
                map[s.charAt(j) - 'a']++;
                ans += beauty(map);
            }
        }

        return ans;
    }

    private int beauty(int[] map) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int count : map) {
            if (count > 0) {
                min = Math.min(min, count);
                max = Math.max(max, count);
            }
        }

        return max - min;
    }
}
```
