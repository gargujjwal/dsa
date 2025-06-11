---
id: sum-of-subarray-minimum
aliases: []
tags: []
layout: default
title: Sum of Subarray Minimum
---

## Optimal Solution

```java
class Solution {
  private static final int MOD = 1000000007;

  public int sumSubarrayMins(int[] arr) {
    int[] prevSmallerElements = prevSmallerEqualElementIdx(arr);
    int[] nextSmallerElements = nextSmallerElementIdx(arr);

    long ans = 0;
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      int left = i - prevSmallerElements[i];
      int right = nextSmallerElements[i] - i;
      ans = (ans + ((left * right * 1L * arr[i])) % MOD) % MOD;
    }

    return (int) ans;
  }

  // modified
  private int[] prevSmallerEqualElementIdx(final int[] arr) {
    int[] ans = new int[arr.length];
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
      ans[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(i);
    }

    return ans;
  }

  // modified
  private int[] nextSmallerElementIdx(final int[] arr) {
    int[] ans = new int[arr.length];
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = arr.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
      ans[i] = stack.isEmpty() ? arr.length : stack.peek();
      stack.push(i);
    }

    return ans;
  }
}
```
