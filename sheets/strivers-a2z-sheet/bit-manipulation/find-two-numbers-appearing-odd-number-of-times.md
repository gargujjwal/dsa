---
id: find-two-numbers-appearing-odd-number-of-times
aliases: []
tags: []
layout: default
title: Find Two Numbers Appeareing Odd Number of Times
---

## Optimal Solution

```java
class Solution {
  public int[] twoOddNum(int arr[], int n) {
    // get xor of both elements
    int xor = 0;
    for (int i : arr) xor ^= i;

    // group with right most bit of xor, is SET
    int grpA = 0;
    // group with right most bit of xor, is UNSET
    int grpB = 0;

    int mask = xor ^ (xor & (xor - 1));
    for (int i : arr)
      if ((i & mask) == 0) grpA ^= i;
      else grpB ^= i;

    return new int[] {Math.max(grpA, grpB), Math.min(grpA, grpB)};
  }
}
```
