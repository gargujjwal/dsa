---
id: peak-element-ii
aliases: []
tags: []
layout: default
title: Peak Element II
---

## Brute Solution, T:O(n \* m)

```python
class Solution:
    def findPeakGrid(self, mat: List[List[int]]) -> List[int]:
        n = len(mat)
        m = len(mat[0])

        for i in range(n):
            for j in range(m):
                if self.is_peak(mat, i, j):
                    return [i, j]

        return [-1, -1]

    def is_peak(self, mat: List[List[int]], row: int, col: int) -> bool:
        n = len(mat)
        m = len(mat[0])

        ans = True
        # top element
        if row != 0:
            ans = mat[row][col] > mat[row - 1][col]
        # left element
        if col != 0:
            ans &= mat[row][col] > mat[row][col - 1]
        # bottom element
        if row != n - 1:
            ans &= mat[row][col] > mat[row + 1][col]
        # right element
        if col != m - 1:
            ans &= mat[row][col] > mat[row][col + 1]

        return ans
```

## Optimal Solution, T:O(n log m)

```python
class Solution:
    def findPeakGrid(self, mat: List[List[int]]) -> List[int]:
        m = len(mat[0])

        left = 0
        right = m - 1
        while left <= right:
            mid = (left + right) >> 1

            row = self.max_in_col(mat, mid)
            cmp_peak = self.is_peak(mat, row, mid)
            if cmp_peak == 1:
                left = mid + 1
            elif cmp_peak == -1:
                right = mid - 1
            else:
                return [row, mid]

        return [-1, -1]

    def max_in_col(self, mat: List[List[int]], col: int) -> int:
        max_el = 0
        max_el_idx = -1
        for i in range(len(mat)):
            if mat[i][col] > max_el:
                max_el = mat[i][col]
                max_el_idx = i

        return max_el_idx

    def is_peak(self, mat: List[List[int]], row: int, col: int) -> int:
        m = len(mat[0])

        # left element
        if col != 0 and mat[row][col] < mat[row][col - 1]:
            return -1

        # right element
        if col != m - 1 and mat[row][col] < mat[row][col + 1]:
            return 1

        return 0
```
