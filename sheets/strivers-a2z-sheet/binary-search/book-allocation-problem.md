---
id: book-allocation-problem
aliases: []
tags: []
layout: default
title: Book Allocation Problem
---

## Brute Solution

## Optimal Solution

```python
class Solution:
    def findPages(self, arr: List[int], k: int) -> int:
        # book allocation impossible
        if k > len(arr):
            return -1
        elif k == 1:
            return sum(arr)
        elif k == len(arr):
            return max(arr)

        low = max(arr)
        high = sum(arr)
        while low <= high:
            mid = (low + high) >> 1
            students = self.countStudents(arr, mid)
            if students > k:
                low = mid + 1
            else:
                high = mid - 1
        return low

    def countStudents(self, arr: List[int], pages: int):
        n = len(arr)  # size of array
        students = 1
        pagesStudent = 0
        for i in range(n):
            if pagesStudent + arr[i] <= pages:
                # add pages to current student
                pagesStudent += arr[i]
            else:
                # add pages to next student
                students += 1
                pagesStudent = arr[i]
        return students
```
