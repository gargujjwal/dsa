---
id: largest-odd-numbers
aliases: []
tags: []
layout: default
title: Largest Odd Number in String
---

## Optimal Solution

```java
public String largestOddNumber(String num) {
    int i = num.length() - 1;
    while (i >= 0) {
        int digit = Character.digit(num.charAt(i), 10);
        if (digit % 2 != 0) {
            break;
        }
        i--;
    }

    if (i == -1) {
        return "";
    }

    return num.substring(0, i + 1);
}
```
