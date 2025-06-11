---
id: next-greater-element-ii
aliases: []
tags: []
layout: default
title: Next Greater Element II
---

## Brute Solution

```java
class Solution {
  public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;

    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      ans[i] = -1;
      for (int j = (i + 1) % n; j != i; j = (j + 1) % n)
        if (nums[j] > nums[i]) {
          ans[i] = nums[j];
          break;
        }
    }
    return ans;
  }
}
```

## Optimal Solution

```java
class Solution {
  public int[] nextGreaterElements(int[] nums) {
    Deque<Integer> frontStack = new ArrayDeque<>();
    Deque<Integer> backStack = new ArrayDeque<>();
    int[] ans = new int[nums.length];

    // push all elements in front stack
    for (int i = nums.length - 1; i >= 0; i--) frontStack.push(nums[i]);

    for (int i = nums.length - 1; i >= 0; i--) {
      // start to look answer from right side
      while (!backStack.isEmpty() && backStack.peek() <= nums[i]) {
        backStack.pop();
      }
      // couldn't find answer in right side
      if (backStack.isEmpty()) {
        // find ans in frontStack
        while (!frontStack.isEmpty() && frontStack.peek() <= nums[i]) {
          frontStack.pop();
        }
        if (frontStack.isEmpty()) {
          ans[i] = -1;
        } else {
          ans[i] = frontStack.peek();
        }
      } else {
        ans[i] = backStack.peek();
      }
      backStack.push(nums[i]);
    }

    return ans;
  }
}
```
