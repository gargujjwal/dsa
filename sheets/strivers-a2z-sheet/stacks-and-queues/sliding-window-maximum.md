---
id: sliding-window-maximum
aliases: []
tags: []
layout: default
title: Sliding Window Maximum
---

## Brute Solution

```java
class Solution {
  public static int[] maxSlidingWindow(int[] nums, int k) {
    int[] window = new int[nums.length - k + 1];
    for (int i = k - 1, j = 0; i < nums.length; i++, j++) window[j] = max(nums, i - k + 1, i);
    return window;
  }

  private static int max(int[] arr, int start, int end) {
    int ans = Integer.MIN_VALUE;
    for (int i = start; i <= end; i++) ans = Math.max(ans, arr[i]);
    return ans;
  }
}
```

## Optimal Solution

```java

```
