---
id: asteriod-collision
aliases: []
tags: []
layout: default
title: Asteriod Collision
---

## Brute Solution

```java
public class Solution {
  public int[] asteroidCollision(int[] arr) {
    Deque<List<Integer>> stack = new ArrayDeque<>();

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > 0) {
        stack.push(List.of(arr[i], i));
        continue;
      }

      while (!stack.isEmpty() && stack.peek().getFirst() < -arr[i]) {
        List<Integer> el = stack.pop();
        // mark that asteroid destroyed
        arr[el.getLast()] = 0;
      }

      // now stack only has elements >= to current asteroid
      // current asteroid destroyed all others towards its left
      if (stack.isEmpty()) continue;
      if (stack.peek().getFirst() == -arr[i]) {
        // both asteroid should be destroyed
        // current asteroid will explode in next line
        List<Integer> el = stack.pop();
        arr[el.getLast()] = 0;
      }
      // asteroid in stack is greater so kill current asteroid
      arr[i] = 0;
    }

    return Arrays.stream(arr).filter(i -> i != 0).toArray();
  }
}
```

## Optimal Solution

- Roughly same as above, except I also store -ve values in stack so that
  at the end of iteration my entire answer is stored in stack

```java
public class Solution {
  public int[] asteroidCollision(int[] arr) {
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > 0) {
        stack.push(arr[i]);
        continue;
      }

      // mark that asteroid destroyed
      while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -arr[i]) stack.pop();

      // now stack only has elements >= to current asteroid
      // current asteroid destroyed all others towards its left
      if (stack.isEmpty()) {
        stack.push(arr[i]);
        continue;
      } else if (stack.peek() == -arr[i]) {
        // both asteroid should be destroyed
        // current asteroid will explode in next line
        stack.pop();
      } else if (stack.peek() < 0) {
        stack.push(arr[i]);
      }
      // asteroid in stack is greater so kill current asteroid
    }

    // modify the same array and return it
    int[] ans = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) ans[i] = stack.pop();

    return ans;
  }
}
```
