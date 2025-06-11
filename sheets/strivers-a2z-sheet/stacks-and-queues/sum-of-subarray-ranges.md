---
id: sum-of-subarray-ranges
aliases: []
tags: []
layout: default
title: Sum of Subarray Ranges
---

## Brute Solution

```java
class Solution {
  public long subArrayRanges(int[] arr) {
    long ans = 0;
    for (int i = 0; i < arr.length; i++) {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      for (int j = i; j < arr.length; j++) {
        min = Math.min(min, arr[j]);
        max = Math.max(max, arr[j]);
        ans += (long) max - min;
      }
    }

    return ans;
  }
}
```

## Optimal Solution

```java
import java.util.*;

class Solution {
  public long subArrayRanges(final int[] arr) {
    int[] prevLargerEqualElems = getPrevLargerEqualElems(arr);
    int[] nextLargerElems = getNextLargerElems(arr);
    int[] prevSmallerEqualElems = getPrevSmallerEqualElems(arr);
    int[] nextSmallerElems = getNextSmallerElems(arr);

    long ans = 0L;
    for (int i = 0; i < arr.length; i++) {
      // calculate minimum contribution
      int left = i - prevSmallerEqualElems[i];
      int right = nextSmallerElems[i] - i;
      long minimumContri = left * right * 1L * arr[i];

      // calculate maximum contribution
      left = i - prevLargerEqualElems[i];
      right = nextLargerElems[i] - i;
      long maxContri = left * right * 1L * arr[i];

      ans += maxContri - minimumContri;
    }

    return ans;
  }

  public int[] getPrevLargerEqualElems(final int[] arr) {
    int n = arr.length;
    Deque<Integer> st = new ArrayDeque<>();
    int[] ans = new int[n];

    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && arr[st.peek()] < arr[i]) st.pop();
      ans[i] = st.isEmpty() ? -1 : st.peek();
      st.push(i);
    }

    return ans;
  }

  public int[] getNextLargerElems(final int[] arr) {
    int n = arr.length;
    Deque<Integer> st = new ArrayDeque<>();
    int[] ans = new int[n];

    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && arr[st.peek()] <= arr[i]) st.pop();
      ans[i] = st.isEmpty() ? n : st.peek();
      st.push(i);
    }

    return ans;
  }

  public int[] getPrevSmallerEqualElems(final int[] arr) {
    int n = arr.length;
    Deque<Integer> st = new ArrayDeque<>();
    int[] ans = new int[n];

    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && arr[st.peek()] > arr[i]) st.pop();
      ans[i] = st.isEmpty() ? -1 : st.peek();
      st.push(i);
    }

    return ans;
  }

  public int[] getNextSmallerElems(final int[] arr) {
    int n = arr.length;
    Deque<Integer> st = new ArrayDeque<>();
    int[] ans = new int[n];

    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
      ans[i] = st.isEmpty() ? n : st.peek();
      st.push(i);
    }

    return ans;
  }
}
```
