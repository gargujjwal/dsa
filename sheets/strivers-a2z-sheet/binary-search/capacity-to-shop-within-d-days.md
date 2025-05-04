---
id: capacity-to-ship-within-d-days
aliases: []
tags: []
layout: default
title: Capacity to Ship Package Within D Days
---

## Brute Solution

```python
class Solution:
    def shipWithinDays(self, weights: List[int], days: int) -> int:
        # try all capacity between arr[0] to maximum
        i = min(weights)
        while True:
            if self._get_days_to_ship(weights, i) <= days:
                return i
            i += 1

    def _get_days_to_ship(self, weights: List[int], capacity: int) -> int:
        days = 0
        weight_loaded = 0
        for i in weights:
            # can't carry if don't have capacity
            if i > capacity:
                return sys.maxsize

            weight_loaded += i

            if weight_loaded == capacity:
                weight_loaded = 0
                days += 1
            elif weight_loaded > capacity:
                days += 1
                weight_loaded = i

        if weight_loaded > 0:
            days += 1

        return days
```

## Optimal Solution

```python
class Solution:
    def shipWithinDays(self, weights: List[int], days: int) -> int:
        # set the limits
        left = 0
        right = 0
        for i in weights:
            left = max(left, i)
            right += i

        ans = 0
        while left <= right:
            mid = (left + right) >> 1

            days_to_ship = self._get_days_to_ship(weights, mid)
            if days_to_ship <= days:
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans

    def _get_days_to_ship(self, weights: List[int], capacity: int) -> int:
        days = 0
        weight_loaded = 0
        for i in weights:
            # can't carry if don't have capacity
            if i > capacity:
                return sys.maxsize

            weight_loaded += i

            if weight_loaded == capacity:
                weight_loaded = 0
                days += 1
            elif weight_loaded > capacity:
                days += 1
                weight_loaded = i

        if weight_loaded > 0:
            days += 1

        return days
```
