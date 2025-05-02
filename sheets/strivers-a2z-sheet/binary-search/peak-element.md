---
id: peak-element
aliases: []
tags: []
layout: default
title: Peak Element
---

## Brute Solution

```python
def findPeakElement(arr: List[int]) -> int:
    n = len(arr)
    if n == 1:
        return 0

    # check manually for both extremes
    if arr[0] > arr[1]:
        return 0
    if arr[-1] > arr[-2]:
        return n - 1

    for i in range(1, n - 1):
        if arr[i] > arr[i - 1] and arr[i] > arr[i + 1]:
            return i

    return -1
```

## Optimal Solution

```python
def findPeakElement(arr: List[int]) -> int:
    n = len(arr)
    if n == 1:
        return 0

    # check manually for both extremes
    if arr[0] > arr[1]:
        return 0
    if arr[-1] > arr[-2]:
        return n - 1

    left = 1
    right = n - 2
    while left <= right:
        mid = (left + right) >> 1

        if arr[mid] > arr[mid + 1] and arr[mid] > arr[mid - 1]:
            return mid
        if arr[mid] > arr[mid - 1]:
            left = mid + 1
        else:
            # this also covers the test case where mid lies in trough (V)
            # and is smaller than both mid - 1 and mid + 1, it easily could
            # have been `left = mid + 1`
            right = mid - 1

    return -1

```
