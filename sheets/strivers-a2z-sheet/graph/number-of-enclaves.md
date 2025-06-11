---
id: number-of-enclaves
aliases: []
tags: []
layout: default
title: Number of Enclaves
---

## Solution

```java
class Solution {

  private static final int SEA = 0;
  private static final int LAND = 1;

  public int numEnclaves(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    boolean[][] visited = new boolean[m][n];

    // go around the edges
    // top boundary
    for (int i = 0; i < n; i++) {
      if (visited[0][i] || mat[0][i] == SEA) continue;
      markVisitedDfs(0, i, visited, mat);
    }

    // right boundary
    for (int i = 0; i < m; i++) {
      if (visited[i][n - 1] || mat[i][n - 1] == SEA) continue;
      markVisitedDfs(i, n - 1, visited, mat);
    }

    // bottom boundary
    for (int i = 0; i < n; i++) {
      if (visited[m - 1][i] || mat[m - 1][i] == SEA) continue;
      markVisitedDfs(m - 1, i, visited, mat);
    }

    // left boundary
    for (int i = 0; i < m; i++) {
      if (visited[i][0] || mat[i][0] == SEA) continue;
      markVisitedDfs(i, 0, visited, mat);
    }

    // mark every non-visited cell as X
    int cnt = 0;
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) if (!visited[i][j] && mat[i][j] == LAND) cnt++;

    return cnt;
  }

  private void markVisitedDfs(int row, int col, boolean[][] visited, int[][] mat) {
    visited[row][col] = true;
    var adjacentCells = new AdjacentCellsIterator(row, col, mat.length, mat[0].length);
    while (adjacentCells.hasNext()) {
      var neighbour = adjacentCells.next();
      if (mat[neighbour.row][neighbour.col] == LAND && !visited[neighbour.row][neighbour.col])
        markVisitedDfs(neighbour.row, neighbour.col, visited, mat);
    }
  }
}
```
