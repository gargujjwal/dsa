---
id: no-of-islands
aliases: []
tags: []
layout: default
title: No of Islands
---

## Solution

- Point to store each island in a set so we only count the new one

```java
class Solution {

  int countDistinctIslands(int[][] mat) {
    int n = mat.length;
    int m = mat[0].length;
    boolean[][] visited = new boolean[n][m];
    Set<List<List<Integer>>> islands = new HashSet<>();

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 0 || visited[i][j]) continue;

        List<List<Integer>> currPath = new ArrayList<>();
        dfs(i, j, visited, mat, currPath);

        // subtract source vertex from each
        for (List<Integer> v : currPath) {
          v.set(0, v.get(0) - i);
          v.set(1, v.get(1) - j);
        }
        islands.add(currPath);
      }

    return islands.size();
  }

  private void dfs(
      int row, int col, boolean[][] visited, int[][] mat, List<List<Integer>> currPath) {
    visited[row][col] = true;
    currPath.add(new ArrayList<>(List.of(row, col)));

    var adjacentCells = new AdjacentCellsIterator(row, col, mat.length, mat[0].length);
    while (adjacentCells.hasNext()) {
      var next = adjacentCells.next();

      if (mat[next.row][next.col] == 1 && !visited[next.row][next.col])
        dfs(next.row, next.col, visited, mat, currPath);
    }
  }
}
```
