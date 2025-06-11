---
id: filling-flood
aliases: []
tags: []
layout: default
title: Filling Flood
---

## Solution

```java
record Coordinate(int i, int j) {}

class Solution {
  private static final int[] dRow = {-1, 0, 1, 0};
  private static final int[] dCol = {0, +1, 0, -1};

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int m = image.length;
    int n = image[0].length;

    // get hold of original color
    int initColor = image[sr][sc];
    if (initColor == color) return image;
    image[sr][sc] = color;

    Deque<Coordinate> q = new ArrayDeque<>();
    q.offer(new Coordinate(sr, sc));
    while (!q.isEmpty()) {
      Coordinate c = q.poll();

      // check left, right, up & bottom
      for (int j = 0; j < 4; j++) {
        int row = c.i() + dRow[j];
        int col = c.j() + dCol[j];

        // invalid indices
        if (row < 0 || row >= m || col < 0 || col >= n) continue;
        // only change color if it matches original color
        if (image[row][col] == initColor) {
          image[row][col] = color;
          q.offer(new Coordinate(row, col));
        }
      }
    }

    return image;
  }
}
```
