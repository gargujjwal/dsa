---
id: count-number-of-strings
aliases: []
tags: []
layout: default
title: Count Number of Substrings
---

## Brute Solution

```java
public class Solution {

    public static int countDistinctSubstrings(String s) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                set.add(s.substring(i, j + 1));
            }
        }

        return set.size() + 1;
    }
}
```

## Optimal Solution
