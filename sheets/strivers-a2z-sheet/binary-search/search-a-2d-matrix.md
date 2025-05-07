---
id: search-a-2d-matrix
aliases: []
tags: []
layout: default
title: Search in a 2D matrix
---

## Better Solution, T:O(log n \* log m)

```python
class Solution:
    def searchMatrix(self, mat: List[List[int]], target: int) -> bool:
        left = 0
        right = len(mat) - 1
        while left <= right:
            mid = (left + right) >> 1

            if mat[mid][0] > target:
                right = mid - 1
            else:
                if self.bin_search(mat[mid], target):
                    return True
                left = mid + 1

        return False

    def bin_search(self, arr: List[int], target: int) -> bool:
        left = 0
        right = len(arr) - 1
        while left <= right:
            mid = (left + right) >> 1
            if arr[mid] > target:
                right = mid - 1
            elif arr[mid] < target:
                left = mid + 1
            else:
                return True

        return False
```

## Optimal Solution, T:O(log (n\*m) )

```python
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        left = 0
        right = len(matrix) * len(matrix[0]) - 1
        while left <= right:
            mid = (left + right) >> 1
            row, col = divmod(mid, len(matrix[0]))
            el = matrix[row][col]
            if el < target:
                left = mid + 1
            elif el > target:
                right = mid - 1
            else:
                return True

        return False
```
