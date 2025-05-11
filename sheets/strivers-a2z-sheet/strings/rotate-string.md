---
id: rotate-string
aliases: []
tags: []
layout: default
title: Rotate String
---

## Brute Solution

```java
class Solution {
    public boolean rotateString(String s, String goal) {
        StringBuilder original = new StringBuilder(s);
        StringBuilder sb = new StringBuilder(s);
        do {
            rotate(sb);
            if (sb.toString().equals(goal)) {
                return true;
            }
        } while (sb.compareTo(original) != 0);

        return false;
    }

    private void rotate(StringBuilder s) {
        char lastChar = s.charAt(s.length() - 1);
        s.deleteCharAt(s.length() - 1);
        s.insert(0, lastChar);
    }
}
```

## Better Solution

```java
class Solution {
    public boolean rotateString(String org, String goal) {
        // can't make goal from org, if not of same length
        if (org.length() != goal.length()) {
            return false;
        }
        for (int i = 0; i < org.length(); i++) {
            if (org.charAt(i) == goal.charAt(0)) {
                if (isRotated(org, goal, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRotated(String org, String goal, int startIdx) {
        int i = startIdx;
        int j = 0;
        do {
            if (org.charAt(i) != goal.charAt(j)) {
                return false;
            }
            i = (i + 1) % org.length();
            j++;
        } while (i != startIdx);
        return true;
    }
}
```

## Optimal Solution

```java
class Solution {
    public boolean rotateString(String org, String goal) {
        if (org.length() != goal.length()) {
            return false;
        }
        return (org.concat(org).contains(goal));
    }
}
```
