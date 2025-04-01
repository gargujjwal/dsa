---
id: maximum-product-subarray
aliases: []
tags: []
layout: default
title: Minimum in Rotated Sorted Array
---

## Brute Solution T:O(n), S:O(1)

```python
from typing import List


class Solution:
    def findMin(self, nums: List[int]) -> int:
        return min(nums)
```

## Optimal Solution T:O(log n), S:O(1)

Using binary search, first find out which half of array is sorted and based on that
find the minimum and shorten the search space

```python
import sys
from typing import List


class Solution:
    def findMin(self, nums: List[int]) -> int:
        minimum = sys.maxsize

        left = 0
        right = len(nums) - 1
        while left <= right:
            mid = (left + right) >> 1

            # already sorted array
            if nums[left] <= nums[right]:
                minimum = min(minimum, nums[left])
                break

            # find out which part is sorted
            if nums[left] <= nums[mid]:
                minimum = min(minimum, nums[left])
                left = mid + 1
            else:
                right = mid - 1
                minimum = min(minimum, nums[mid])

        return minimum
```
