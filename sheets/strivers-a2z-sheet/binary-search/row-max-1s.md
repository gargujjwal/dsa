---
id: row-max-1s
aliases: []
tags: []
layout: default
title: Row with Max 1s
---

## Brute Solution

```python
class Solution:
    def rowWithMax1s(self, mat: List[List[int]]):
        ans = -1
        max_1s = -1
        for i, row in enumerate(mat):
            first_idx_of_1 = self.get_first_idx_of_1(row)
            if first_idx_of_1 != -1:
                cnt = len(row) - first_idx_of_1
                if cnt > max_1s:
                    max_1s = cnt
                    ans = i

        return ans

    def get_first_idx_of_1(self, arr: List[int]) -> int:
        ans = -1
        left = 0
        right = len(arr) - 1
        while left <= right:
            mid = (left + right) >> 1
            if arr[mid] == 1:
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans
```
