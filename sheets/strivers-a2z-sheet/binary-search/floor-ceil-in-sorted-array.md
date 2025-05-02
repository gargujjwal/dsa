---
id: floor-ceil-in-sorted-array
aliases: []
tags: []
layout: default
title: Floor Ceil in Sorted Array
---

```python
def findFloor(arr: List[int], x: int) -> int:
    if arr[0] > x:
        return -1

    left = 0
    right = len(arr) - 1

    ans = -1
    while left <= right:
        mid = (left + right) >> 1

        if arr[mid] <= x:
            ans = mid
            left = mid + 1
        else:
            right = mid - 1

    return ans
```
