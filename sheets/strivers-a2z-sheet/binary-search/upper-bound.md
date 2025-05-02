---
id: upper-bound
aliases: []
tags: []
layout: default
title: Upper Bound
---

```python
def upperBound(arr: List[int], target: int) -> int:
    left = 0
    right = len(arr) - 1

    # question states that if no upper bound exists then return length of
    # array
    ans = len(arr)
    while left <= right:
        mid = (left + right) >> 1

        if arr[mid] > target:
            ans = mid
            right = mid - 1
        else:
            left = mid + 1

    return ans
```
