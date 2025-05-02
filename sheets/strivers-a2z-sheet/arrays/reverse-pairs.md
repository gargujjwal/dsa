---
id: reverse-pairs
aliases: []
tags: []
layout: default
title: Reverser Pairs
---

## Brute Solution

```python
def reversePairs(arr: List[int]) -> int:
    n = len(arr)

    ans = 0
    for i in range(0, n):
        for j in range(i + 1, n):
            if arr[i] > 2 * arr[j]:
                ans += 1

    return ans
```

## Optimal Solution

Refer Count Inversion for understanding

```python
def reversePairs(arr: List[int]) -> int:
    ans = Solution._modified_merge_sort(arr, 0, len(arr) - 1)
    return ans

def _modified_merge_sort(arr: List[int], start: int, end: int) -> int:
    cnt = 0
    if start < end:
        mid = (start + end) >> 1
        cnt += Solution._modified_merge_sort(arr, start, mid)
        cnt += Solution._modified_merge_sort(arr, mid + 1, end)
        cnt += Solution._merge(arr, start, mid, end)

    return cnt

def _merge(arr: List[int], start: int, mid: int, end: int) -> int:
    left_arr = arr[start : mid + 1]
    right_arr = arr[mid + 1 : end + 1]

    # inversion logic
    cnt = 0
    i = 0
    j = 0
    while i < len(left_arr) and j < len(right_arr):
        if left_arr[i] > 2 * right_arr[j]:
            cnt += len(left_arr) - i
            j += 1
        else:
            i += 1

    i = 0
    j = 0
    k = start
    while i < len(left_arr) and j < len(right_arr):
        if left_arr[i] > right_arr[j]:
            arr[k] = right_arr[j]
            j += 1
        else:
            arr[k] = left_arr[i]
            i += 1
        k += 1

    while i < len(left_arr):
        arr[k] = left_arr[i]
        i += 1
        k += 1

    while j < len(right_arr):
        arr[k] = right_arr[j]
        j += 1
        k += 1

    return cnt  # inversion logic
```
