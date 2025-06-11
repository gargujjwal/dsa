---
id: maximal-rectangle
aliases: []
tags: []
layout: default
title: Maximal Rectangle
---

## Brute Solution

```java
class Solution {
  public int maximalRectangle(char[][] mat) {
    int n = mat.length;
    int m = mat[0].length;

    int ans = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == '0') continue;
        // try to make a rectangle by looking at its right and bottom index
        ans = Math.max(ans, getMaxRectangleArea(mat, i, j));
      }
    }

    return ans;
  }

  // try to form a rectangle from this point on
  private int getMaxRectangleArea(char[][] mat, int row, int col) {
    int ans = 0;
    int length = 0;
    int breadth = Integer.MAX_VALUE;
    for (int i = row; i < mat.length; i++) {
      if (mat[i][col] == '0') break;
      length++;
      int currBreadth = 0;
      for (int j = col; j < mat[0].length; j++) {
        if (mat[i][j] == '1') currBreadth++;
        else break;
      }
      breadth = Math.min(breadth, currBreadth);
      int area = length * breadth;
      ans = Math.max(ans, area);
    }

    return ans;
  }
}
```

## Optimal Solution

```java
class Solution {
  public int maximalRectangle(char[][] mat) {
    int ans = 0;
    int[] histogram = new int[mat[0].length];

    for (int i = 0; i < mat.length; i++) {
      // prepare current histogram chart
      for (int j = 0; j < mat[0].length; j++)
        if (mat[i][j] == '0') histogram[j] = 0;
        else histogram[j] += 1;

      ans = Math.max(ans, largestRectangleArea(histogram));
    }

    return ans;
  }

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
