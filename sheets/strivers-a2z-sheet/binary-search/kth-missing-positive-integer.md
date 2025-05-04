---
id: capacity-to-ship-within-d-days
aliases: []
tags: []
layout: default
title: Capacity to Ship Package Within D Days
---

## Brute Solution

```python
def findKthPositive(arr: List[int], k: int) -> int:
    missing_num: List[int] = list()
    num = 0
    for i in arr:
        num += 1
        while num < i:
            missing_num.append(num)
            num += 1

    # missing number lies beyond the array
    if len(missing_num) < k:
        k -= len(missing_num)
        return arr[-1] + k
    else:
        return missing_num[k - 1]
```

## Better Solution

```python
def findKthPositive(arr: List[int], k: int) -> int:
    for i in range(len(arr)):
        if arr[i] <= k:
            k += 1
        else:
            break

    return k
```

## Optimal Solution

- See the vid

```python
class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:
        n = len(arr)

        left = 0
        right = n - 1
        while left <= right:
            mid = (left + right) >> 1

            numbers_missing = self._get_numbers_missing(arr[mid], mid)
            if numbers_missing < k:
                left = mid + 1
            else:
                right = mid - 1

        return right + 1 + k

    def _get_numbers_missing(self, num: int, idx: int):
        return (num - 1) - idx
```
