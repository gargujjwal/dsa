---
id: binary-search
aliases: []
tags: []
layout: default
title: Binary Search
---

```python
def search(arr: List[int], target: int) -> int:
    left = 0
    right = len(arr) - 1

    while left <= right:
        mid = (left + right) >> 1

        if target > arr[mid]:
            left = mid + 1
        elif target < arr[mid]:
            right = mid - 1
        else:
            return mid

    return -1
```
