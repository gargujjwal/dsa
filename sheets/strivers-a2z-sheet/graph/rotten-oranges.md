---
id: rotten-oranges
aliases: []
tags: []
layout: default
title: Rotten Oranges
---

## BFS Solution

```java
record Coordinate(int i, int j) {}

class Solution {

  public int orangesRotting(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    Deque<Coordinate> q = new ArrayDeque<>();

    // add all the bad oranges to q
    int freshOrangeCnt = 0;
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) freshOrangeCnt++;
        else if (grid[i][j] == 2) {
          Coordinate c = new Coordinate(i, j);
          q.offer(c);
        }
      }

    // if no orange is present then no further processing
    if (freshOrangeCnt + q.size() == 0) return 0;

    // start degrading other oranges
    int timePassed = -1;
    int[] dRow = {-1, 0, 1, 0};
    int[] dCol = {0, +1, 0, -1};

    while (!q.isEmpty()) {

      // start rotting from all oranges
      int size = q.size();
      for (int i = 0; i < size; i++) {
        Coordinate c = q.poll();

        // check left, right, up & bottom
        for (int j = 0; j < 4; j++) {
          int row = c.i() + dRow[j];
          int col = c.j() + dCol[j];

          // invalid indices
          if (row < 0 || row >= m || col < 0 || col >= n) continue;
          if (grid[row][col] == 1) {
            grid[row][col] = 2;
            q.offer(new Coordinate(row, col));
            freshOrangeCnt--;
          }
        }
      }
      timePassed++;
    }

    // now all the oranges that could be rotten are rotten
    return freshOrangeCnt == 0 ? timePassed : -1;
  }
}
```
