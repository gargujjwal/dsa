---
id: single-element-in-sorted-array
aliases: []
tags: []
layout: default
title: Single Element in Sorted Array
---

## Optimal Solution

```python
def singleNonDuplicate(arr: List[int]) -> int:
    if len(arr) == 1:
        return arr[0]

    n = len(arr)
    # manual checks for both extremes
    if arr[0] != arr[1]:
        return arr[0]
    if arr[-1] != arr[-2]:
        return arr[-1]

    # trim down array to arr[1:n-1] to avoid conditionals
    left = 1
    right = n - 2
    while left <= right:
        mid = (left + right) >> 1

        if Solution._is_even(mid):
            if arr[mid] == arr[mid + 1]:
                left = mid + 1
            else:
                right = mid - 1
        else:
            if arr[mid] == arr[mid - 1]:
                left = mid + 1
            else:
                right = mid - 1

    return arr[left]
```
