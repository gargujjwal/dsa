---
id: next-greater-element
aliases: []
tags: []
layout: default
title: Next Greater Element
---

## Brute Solution

```java
class Solution {
  public int[] nextGreaterElement(int[] arr1, int[] arr2) {
    int n1 = arr1.length;
    int n2 = arr2.length;

    int[] ans = new int[n1];
    for (int i = 0; i < n1; i++) {
      int j = 0;
      for (; j < n2; j++) if (arr1[i] == arr2[j]) break;

      int currAns;
      if (j == n2) currAns = -1;
      else {
        // find next greater element
        int k = j;
        for (; k < n2; k++) if (arr2[k] > arr2[j]) break;
        currAns = k == n2 ? -1 : arr2[k];
      }

      ans[i] = currAns;
    }

    return ans;
  }
}
```

## Brute Solution II

```java
class Solution {
  public int[] nextGreaterElement(int[] arr1, int[] arr2) {
    int n1 = arr1.length;
    int n2 = arr2.length;

    // create a hashmap for arr2, key=arr2[i], val=next greater element
    Map<Integer, Integer> map = new HashMap<>();

    map.put(arr2[n2 - 1], -1);
    for (int i = n2 - 2; i >= 0; i--) {
      int nextGreater = -1;
      if (arr2[i + 1] > arr2[i]) {
        nextGreater = arr2[i + 1];
      } else {
        int j;
        for (j = i + 1; j < n2; j++) {
          if (arr2[j] > arr2[i]) {
            nextGreater = arr2[j];
            break;
          }
          int nextElNextGreater = map.get(arr2[j]);
          if (nextElNextGreater == -1) {
            nextGreater = -1;
            break;
          } else if (nextElNextGreater > arr2[i]) {
            nextGreater = nextElNextGreater;
            break;
          }
        }
      }
      map.put(arr2[i], nextGreater);
    }

    int[] ans = new int[n1];
    for (int i = 0; i < n1; i++) {
      ans[i] = map.get(arr1[i]);
    }

    return ans;
  }
}
```

## Optimal Solution

```java
class Solution {
  public int[] nextGreaterElement(int[] arr1, int[] arr2) {
    int n1 = arr1.length;
    int n2 = arr2.length;

    Map<Integer, Integer> map = new HashMap<>(n1);

    Deque<Integer> stack = new ArrayDeque<>(n1);
    for (int i = n2 - 1; i >= 0; i--) {
      // remove elements from stack until a greater element is found
      while (!stack.isEmpty() && stack.peek() <= arr2[i]) stack.pop();
      if (stack.isEmpty()) map.put(arr2[i], -1);
      else map.put(arr2[i], stack.peek());

      // add the current element in stack
      stack.push(arr2[i]);
    }

    // put the answers in arr1, to avoid creating a new array, in
    // interview u should not modify the inputs
    for (int i = 0; i < n1; i++) arr1[i] = map.get(arr1[i]);
    return arr1;
  }
}
```
