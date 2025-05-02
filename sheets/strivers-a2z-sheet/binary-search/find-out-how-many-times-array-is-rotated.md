---
id: find-out-how-many-times-array-is-sorted
aliases: []
tags: []
layout: default
title: Find out How Many Times Array is Sorted
---

## Optimal Solution

```python
def findKRotation(arr: List[int]) -> int:
    min_el = {"val": arr[0], "idx": 0}
    left = 0
    right = len(arr) - 1
    while left <= right:
        mid = (left + right) >> 1

        # check if array is completely sorted
        if arr[left] <= arr[right]:
            if arr[left] < min_el["val"]:
                min_el["val"] = arr[left]
                min_el["idx"] = left
            break

        # find the sorted half
        if arr[left] <= arr[mid]:
            # left half is sorted
            if arr[left] < min_el["val"]:
                min_el["val"] = arr[left]
                min_el["idx"] = left
            left = mid + 1
        else:
            # right half is sorted
            if arr[mid] < min_el["val"]:
                min_el["val"] = arr[mid]
                min_el["idx"] = mid
            right = mid - 1

    return min_el["idx"]
```
