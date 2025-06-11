---
id: sudoku-solver
aliases: []
tags: []
layout: default
title: Sudoku Solver
---

## Solution using Backtracking

```java
public class Solution {
  private static final int N = 9;

  public void solveSudoku(char[][] board) {
    helper(0, board);
  }

  public boolean helper(int n, char[][] board) {
    int row = n / 9;
    int col = n % 9;
    if (n == 81) {
      return true;
    }
    if (board[row][col] != '.') {
      return helper(n + 1, board);
    }

    for (char i = '1'; i <= '9'; i++) {
      if (isValidNumber(i, row, col, board)) {
        board[row][col] = i;
        if (helper(n + 1, board)) return true;
        board[row][col] = '.';
      }
    }

    return false;
  }

  private boolean isValidNumber(char num, int row, int col, char[][] board) {
    // Each of the digits 1-9 must occur exactly once in each row
    // Each of the digits 1-9 must occur exactly once in each column.
    for (int i = 0; i < N; i++) if (board[row][i] == num || board[i][col] == num) return false;
    // Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    Map<Integer, List<Integer>> rangeMap =
        Map.of(
            0, List.of(0, 2),
            1, List.of(0, 2),
            2, List.of(0, 2),
            3, List.of(3, 5),
            4, List.of(3, 5),
            5, List.of(3, 5),
            6, List.of(6, 8),
            7, List.of(6, 8),
            8, List.of(6, 8));
    for (int i = rangeMap.get(row).get(0); i <= rangeMap.get(row).get(1); i++)
      for (int j = rangeMap.get(col).get(0); j <= rangeMap.get(col).get(1); j++)
        if (board[i][j] == num) return false;

    return true;
  }
}
```
