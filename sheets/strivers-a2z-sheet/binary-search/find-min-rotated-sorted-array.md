---
id: find-min-rotated-sorted-array
aliases: []
tags: []
layout: default
title: Find minimum in Rotated Sorted Array
---

## Optimal Solution

```python
def findMin(arr: List[int]) -> int:
    ans = arr[0]
    left = 0
    right = len(arr) - 1
    while left <= right:
        mid = (left + right) >> 1

        # check if array is completely sorted
        if arr[left] <= arr[right]:
            ans = min(arr[left], ans)
            break

        # find the sorted half
        if arr[left] <= arr[mid]:
            # left half is sorted
            ans = min(ans, arr[left])
            left = mid + 1
        else:
            # right half is sorted
            ans = min(ans, arr[mid])
            right = mid - 1

    return ans
```
