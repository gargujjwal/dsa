---
id: minimum-days-to-make-m-bouquets
aliases: []
tags: []
layout: default
title: Minimum Days to Make m bouquets
---

## Brute Solution

```python
def smallestDivisor(self, arr: List[int], threshold: int) -> int:
    ans = sys.maxsize
    for i in range(max(arr), 0, -1):
        div_sum = 0
        for j in arr:
            div_sum += ceil(j / i)

        if div_sum <= threshold:
            ans = min(i, ans)
        else:
            break

    return ans
```

## Optimal Solution

```python
class Solution:
    def smallestDivisor(self, arr: List[int], threshold: int) -> int:
        ans = -1
        left = 1
        right = max(arr)
        while left <= right:
            mid = (left + right) >> 1

            div_sum = self._get_division_sum(arr, mid)
            if div_sum > threshold:
                left = mid + 1
            elif div_sum <= threshold:
                ans = mid
                right = mid - 1

        return ans

    def _get_division_sum(self, arr: List[int], divisor: int) -> int:
        div_sum = 0
        for i in arr:
            div_sum += ceil(i / divisor)

        return div_sum
```
