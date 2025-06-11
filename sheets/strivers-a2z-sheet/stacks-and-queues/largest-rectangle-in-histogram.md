---
id: largest-rectangle-in-histogram
aliases: []
tags: []
layout: default
title: Largest Rectangle in Histogram
---

## Brute Solution, T:O(N ^ 2) S:O(1)

```java
class Solution {
  public int largestRectangleArea(int[] heights) {
    int ans = 0;
    for (int i = 0; i < heights.length; i++) {
      int min = Integer.MAX_VALUE;
      for (int j = i; j < heights.length; j++) {
        if (heights[j] == 0) break;
        min = Math.min(min, heights[j]);
        int area = min * (j - i + 1);
        ans = Math.max(ans, area);
      }
    }

    return ans;
  }
}
```

## Better Solution, T:O(4N) S:(2N)

```java
class Solution {
  public int largestRectangleArea(int[] heights) {
    int ans = 0;
    int[] prevSmallerHeightIndices = getPrevStrictlySmallerHeightIndices(heights);
    int[] nextSmallerHeightIndices = getNextStrictlySmallerHeightIndices(heights);

    for (int i = 0; i < heights.length; i++) {
      // subtracted one cuz we're not counting the smaller element
      int left = i - prevSmallerHeightIndices[i] - 1;
      int right = nextSmallerHeightIndices[i] - i - 1;
      int area = heights[i] * (left + right + 1);
      ans = Math.max(ans, area);
    }

    return ans;
  }

  private int[] getPrevStrictlySmallerHeightIndices(int[] heights) {
    int[] ans = new int[heights.length];
    Deque<Integer> st = new ArrayDeque<>();
    for (int i = 0; i < ans.length; i++) {
      while (!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
      ans[i] = st.isEmpty() ? -1 : st.peek();
      st.push(i);
    }

    return ans;
  }

  private int[] getNextStrictlySmallerHeightIndices(int[] heights) {
    int n = heights.length;
    int[] ans = new int[n];
    Deque<Integer> st = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
      ans[i] = st.isEmpty() ? n : st.peek();
      st.push(i);
    }

    return ans;
  }
}
```

## Optimal Solution, T:(2N) S:O(N)

```java
class Solution {
  public int largestRectangleArea(int[] heights) {
    int ans = 0;
    Deque<Integer> st = new ArrayDeque<>();
    for (int i = 0; i < heights.length; i++) {
      while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
        // here we know that for the element in the stack, elem at idx i
        // is NSE(next smaller element), so process them
        int elIdx = st.pop();
        int left = elIdx - (st.isEmpty() ? -1 : st.peek()) - 1;
        int right = i - elIdx - 1;
        int area = heights[elIdx] * (left + right + 1);
        ans = Math.max(ans, area);
      }
      st.push(i);
    }

    // if there are elements left in stack then there exists no next smaller
    // element, but prev smaller element still might exist, which will be
    // the element in the stack before them, if there is
    // If its last element in stack, then there ain't NSE or PSE
    while (!st.isEmpty()) {
      int elIdx = st.pop();
      int left = elIdx - (st.isEmpty() ? -1 : st.peek()) - 1;
      int right = heights.length - elIdx - 1;
      int area = heights[elIdx] * (left + right + 1);
      ans = Math.max(ans, area);
    }

    return ans;
  }
}
```
