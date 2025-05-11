---
id: sort-by-freq-of-char
aliases: []
tags: []
layout: default
title: Sort by Frequency of Characters in a String
---

## Optimal Solution

```java
class Solution {
    public String frequencySort(String s) {
        // populate hashmap and calculate frequency
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }

        // create buckets, freq -> [characters]
        Map<Integer, List<Character>> bucketMap = new HashMap<>();
        for (var entry : map.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            if (bucketMap.containsKey(freq)) {
                bucketMap.get(freq).add(entry.getKey());
            } else {
                bucketMap.put(freq, new ArrayList<>(List.of(c)));
            }
        }

        // recreate string
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = s.length(); i > 0; i--) {
            // look for a bucket with characters which repeat n times
            for (char c : bucketMap.getOrDefault(i, List.of())) {
                // add the current character i times
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}

```
