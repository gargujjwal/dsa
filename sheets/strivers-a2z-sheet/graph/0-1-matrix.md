---
id: 0-1-matrix
aliases: []
tags: []
layout: default
title: 0/1 Matrix
---

```java
record Coordinate(int row, int col) {}

class Solution {
  private static final int[] dRow = {-1, 0, 1, 0};
  private static final int[] dCol = {0, -1, 0, 1};

  public int[][] updateMatrix(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    int[][] ans = new int[m][n];

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (mat[i][j] == 0) ans[i][j] = 0;
        else ans[i][j] = distToNearest0Bfs(mat, new Coordinate(i, j));

    return ans;
  }

  private int distToNearest0Bfs(int[][] mat, Coordinate src) {
    int ans = 0;
    Deque<Coordinate> q = new ArrayDeque<>();
    q.offer(src);
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        Coordinate c = q.poll();

        for (int j = 0; j < 4; j++) {
          int row = c.row() + dRow[j];
          int col = c.col() + dCol[j];

          if (row < 0 || row >= mat.length || col < 0 || col > mat[0].length) continue;

          // if we found 0, then we have ans
          if (mat[row][col] == 0) return ans + 1;
          else q.offer(new Coordinate(row, col));
        }
      }
      ans++;
    }

    return Integer.MAX_VALUE;
  }
}
```

## Optimal Solution

```java
class Solution {

  public int[][] updateMatrix(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    boolean[][] visited = new boolean[m][n];
    // {row, col, dist to nearest 1}
    Deque<int[]> q = new ArrayDeque<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (mat[i][j] == 0) {
          visited[i][j] = true;
          q.offer(new int[] {i, j, 0});
        }
      }
    }

    int[][] distMat = new int[m][n];
    // now run multi source bfs
    while (!q.isEmpty()) {
      int row = q.peek()[0];
      int col = q.peek()[1];
      int dist = q.peek()[2];
      q.poll();

      // set the answer
      distMat[row][col] = dist;

      var adjacentCells = new AdjacentCellsIterator(row, col, m, n);
      while (adjacentCells.hasNext()) {
        // {row, col}
        var next = adjacentCells.next();

        if (!visited[next.row][next.col]) {
          visited[next.row][next.col] = true;
          q.offer(new int[] {next.row, next.col, dist + 1});
        }
      }
    }

    return distMat;
  }
}
```
