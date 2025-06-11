---
id: n-queens
aliases: []
tags: []
layout: default
title: N Queens
---

## Better Solution

```java
class Solution {
  private int n;
  private List<StringBuilder> board;
  private List<List<String>> ans;

  public List<List<String>> solveNQueens(int n) {
    this.ans = new ArrayList<>();
    this.board = IntStream.range(0, n).mapToObj(i -> new StringBuilder(".".repeat(n))).toList();
    this.n = n;

    helper(n, 0);

    return ans;
  }

  private void helper(int queensLeft, int row) {
    if (queensLeft == 0) {
      ans.add(IntStream.range(0, n).mapToObj(i -> this.board.get(i).toString()).toList());
      return;
    }
    if (row >= n) {
      return;
    }

    for (int i = 0; i < n; i++) {
      if (canPlaceQueen(row, i)) {
        board.get(row).setCharAt(i, 'Q');
        helper(queensLeft - 1, row + 1);
        board.get(row).setCharAt(i, '.');
      }
    }
  }

  private boolean canPlaceQueen(int row, int col) {
    // no queen in current row
    if (board.get(row).chars().anyMatch(c -> c == 'Q')) {
      return false;
    }

    // no queen in current column
    if (IntStream.range(0, n).anyMatch(r -> board.get(r).charAt(col) == 'Q')) {
      return false;
    }

    // no queen in both diagonal
    // goes in North-West diagonal, then South East diagonal then
    // North-East Diagonal, then South West diagonal
    int[][] directions = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    for (int[] d : directions) {
      int r = row + d[0];
      int c = col + d[1];
      while (r >= 0 && r < n && c >= 0 && c < n) {
        if (board.get(r).charAt(c) == 'Q') return false;
        r += d[0];
        c += d[1];
      }
    }

    return true;
  }
}

```
