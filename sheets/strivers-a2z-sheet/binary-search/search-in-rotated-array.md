---
id: search-in-rotated-array
aliases: []
tags: []
layout: default
title: Search in Rotated Array
---

# Search in Rotated Sorted Array

## Brute Solution

```python
from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        # look for a number which is just bigger than target in nums array
        just_bigger_el = target
        idx = -1

        for i, el in enumerate(nums):
            if el >= target and el <= just_bigger_el:
                idx = i
                just_bigger_el = el

        return idx
```

## Optimal Solution

- We use binary search in this, but since we can't use it directly we use it
  smartly as follows
- Following usual flow of binary search, we initialize `left=0` and `right=n-1`,
  and whether `arr[mid]==target`, if yes then solved otherwise we find sorted half
  between left or right (it is guaranteed that either will be sorted)
- We check if the target might exist in sorted half
  - if yes then we limit `left` and `right` to sorted half
  - otherwise we start searching in unsorted half by changing `left` or `right`

```python
def search(arr: List[int], target: int) -> int:
    left = 0
    right = len(arr) - 1

    while left <= right:
        mid = (left + right) >> 1

        if arr[mid] == target:
            return mid

        # check which part is sorted
        if arr[left] <= arr[mid]:
            # left part is sorted
            # check if target exists in this subarray
            if target >= arr[left] and target < arr[mid]:
                right = mid - 1
            else:
                # target doesn't exist in left subarray, it might in
                # right subarray
                left = mid + 1
        else:
            # right part is sorted
            if target > arr[mid] and target <= arr[right]:
                left = mid + 1
            else:
                # target doesn't exist in right subarray, it might in
                # left subarray
                right = mid - 1

    return -1
```
