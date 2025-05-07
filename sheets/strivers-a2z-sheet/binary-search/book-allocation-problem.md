---
id: book-allocation-problem
aliases: []
tags: []
layout: default
title: Book Allocation Problem
---

## Brute Solution

```python
class Solution:
    def findPages(self, arr: List[int], k: int) -> int:
        n = len(arr)
        if k == n:
            # no of students equals no of books
            return max(arr)
        elif k > n:
            # no of students > no of books
            return -1
        elif k == 1:
            # all books allocated to one person
            return sum(arr)

        # find the limits
        min_page_lim = arr[0]
        max_page_lim = 0
        for i in range(n):
            min_page_lim = max(min_page_lim, arr[i])
            max_page_lim += arr[i]

        for i in range(min_page_lim, max_page_lim + 1):
            if self.count_students(arr, i) == k:
                return i

        return -1

    def count_students(self, arr: List[int], page_limit: int) -> int:
        prev_pages = 0
        students = 1

        for i in arr:
            if prev_pages + i <= page_limit:
                prev_pages += i
            else:
                students += 1
                prev_pages = i

        return students
```

## Optimal Solution

```python
class Solution:
    def findPages(self, arr: List[int], k: int) -> int:
        n = len(arr)
        if k == n:
            # no of students equals no of books
            return max(arr)
        elif k > n:
            # no of students > no of books
            return -1
        elif k == 1:
            # all books allocated to one person
            return sum(arr)

        # find the limits
        min_page_lim = arr[0]
        max_page_lim = 0
        for i in range(n):
            min_page_lim = max(min_page_lim, arr[i])
            max_page_lim += arr[i]

        left = min_page_lim
        right = max_page_lim
        while left <= right:
            mid = (left + right) >> 1

            if self.count_students(arr, mid) > k:
                left = mid + 1
            else:
                right = mid - 1

        return left

    def count_students(self, arr: List[int], page_limit: int) -> int:
        prev_pages = 0
        students = 1

        for i in arr:
            if prev_pages + i <= page_limit:
                prev_pages += i
            else:
                students += 1
                prev_pages = i

        return students
```
