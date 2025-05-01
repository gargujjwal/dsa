---
id: left-rotate-array-k-places
aliases: []
tags: []
layout: default
title: Left Rotate Array by K Places
---

## Optimal Solution

```python
def rotate(self, nums: List[int], k: int) -> None:
    n = len(nums)
    k %= n
    k = n - k
    self.__reverse(nums, 0, k - 1)
    self.__reverse(nums, k, n - 1)
    self.__reverse(nums, 0, n - 1)

def __reverse(self, arr: List[int], start: int, end: int) -> None:
    while start <= end:
        arr[start], arr[end] = arr[end], arr[start]
        start += 1
        end -= 1
```
