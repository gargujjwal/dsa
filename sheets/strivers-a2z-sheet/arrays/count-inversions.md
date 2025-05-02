---
id: count-inversions
aliases: []
tags: []
layout: default
title: Count Inversions
---

## Brute Solution

```python
def inversionCount(arr: List[int]) -> int:
    n = len(arr)

    ans = 0
    for i in range(0, n):
        for j in range(i + 1, n):
            if arr[i] > arr[j]:
                ans += 1

    return ans
```

## Optimal Solution

- Using global variable which is discourage in interview

```python
class Solution:
    __inversion_count = 0

    def inversionCount(self, arr: List[int]) -> int:
        self._modified_merge_sort(arr, 0, len(arr) - 1)

        return self.__inversion_count

    def _modified_merge_sort(self, arr: List[int], start: int, end: int) -> None:
        if start < end:
            mid = (start + end) >> 1
            self._modified_merge_sort(arr, start, mid)
            self._modified_merge_sort(arr, mid + 1, end)

            self._merge(arr, start, mid, end)

    def _merge(self, arr: List[int], start: int, mid: int, end: int) -> None:
        left_arr = arr[start : mid + 1]
        right_arr = arr[mid + 1 : end + 1]

        i = 0
        j = 0
        # inversion logic here
        while i < len(left_arr) and j < len(right_arr):
            if left_arr[i] > right_arr[j]:
                self.__inversion_count += len(left_arr) - i
                j += 1
            else:
                i += 1

        i = 0
        j = 0
        k = start

        while i < len(left_arr) and j < len(right_arr):
            if left_arr[i] < right_arr[j]:
                arr[k] = left_arr[i]
                i += 1
            else:
                arr[k] = right_arr[j]
                j += 1
            k += 1

        while i < len(left_arr):
            arr[k] = left_arr[i]
            i += 1
            k += 1

        while j < len(right_arr):
            arr[k] = right_arr[j]
            j += 1
            k += 1
```

- Without using global variable

```python
def inversionCount(arr: List[int]) -> int:
    ans = _modified_merge_sort(arr, 0, len(arr) - 1)
    return ans

def _modified_merge_sort( arr: List[int], start: int, end: int) -> int:
    cnt = 0
    if start < end:
        mid = (start + end) >> 1
        cnt += _modified_merge_sort(arr, start, mid)
        cnt += _modified_merge_sort(arr, mid + 1, end)

        cnt += _merge(arr, start, mid, end)

    return cnt

def _merge(arr: List[int], start: int, mid: int, end: int) -> int:
    left_arr = arr[start : mid + 1]
    right_arr = arr[mid + 1 : end + 1]

    i = 0
    j = 0
    k = start
    cnt = 0  # inversion logic

    while i < len(left_arr) and j < len(right_arr):
        if left_arr[i] > right_arr[j]:
            arr[k] = right_arr[j]
            # inversion logic
            cnt += len(left_arr) - i
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
