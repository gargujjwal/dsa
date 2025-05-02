---
id: count-number-of-occurrences
aliases: []
tags: []
layout: default
title: Count number of Occurrences
---

```python
class Solution:
    def countFreq(self, arr: List[int], target: int) -> int:
        first_idx = self.first_occurrence(arr, target)
        if first_idx == -1:
            return 0
        last_idx = self.last_occurrence(arr, target)
        return last_idx - first_idx + 1

    def first_occurrence(self, arr: List[int], target: int) -> int:
        left = 0
        right = len(arr) - 1

        ans = -1
        while left <= right:
            mid = (left + right) >> 1

            if arr[mid] < target:
                left = mid + 1
            elif arr[mid] > target:
                right = mid - 1
            else:
                ans = mid
                right = mid - 1

        return ans

    def last_occurrence(self, arr: List[int], target: int) -> int:
        left = 0
        right = len(arr) - 1

        ans = -1
        while left <= right:
            mid = (left + right) >> 1

            if arr[mid] < target:
                left = mid + 1
            elif arr[mid] > target:
                right = mid - 1
            else:
                ans = mid
                left = mid + 1

        return ans
```
