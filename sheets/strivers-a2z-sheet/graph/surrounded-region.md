---
id: surrounded-region
aliases: []
tags: []
layout: default
title: Surrounded Region
---

## Solution

```java
class Solution {

  private static final char X = 'X';
  private static final char O = 'O';

  public void solve(char[][] board) {
    int m = board.length;
    int n = board[0].length;

    boolean[][] visited = new boolean[m][n];

    // go around the edges
    // top boundary
    for (int i = 0; i < n; i++) {
      if (visited[0][i] || board[0][i] == X) continue;
      markVisitedDfs(0, i, visited, board);
    }

    // right boundary
    for (int i = 0; i < m; i++) {
      if (visited[i][n - 1] || board[i][n - 1] == X) continue;
      markVisitedDfs(i, n - 1, visited, board);
    }

    // bottom boundary
    for (int i = 0; i < n; i++) {
      if (visited[m - 1][i] || board[m - 1][i] == X) continue;
      markVisitedDfs(m - 1, i, visited, board);
    }

    // left boundary
    for (int i = 0; i < m; i++) {
      if (visited[i][0] || board[i][0] == X) continue;
      markVisitedDfs(i, 0, visited, board);
    }

    // mark every non-visited cell as X
    for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if (!visited[i][j]) board[i][j] = X;
  }

  private void markVisitedDfs(int row, int col, boolean[][] visited, char[][] board) {
    visited[row][col] = true;
    var adjacentCells = new AdjacentCellsIterator(row, col, board.length, board[0].length);
    while (adjacentCells.hasNext()) {
      var neighbour = adjacentCells.next();
      if (board[neighbour.row][neighbour.col] == O && !visited[neighbour.row][neighbour.col])
        markVisitedDfs(neighbour.row, neighbour.col, visited, board);
    }
  }
}
```
