---
id: aggressive-cows
aliases: []
tags: []
layout: default
title: Aggressive Cows
---

## Brute Solution

```python
class Solution:
    def aggressiveCows(self, stalls: List[int], cows: int) -> int:
        stalls.sort()
        dist = 1
        while True:
            if self.can_place_cows(stalls, dist, cows):
                dist += 1
            else:
                return dist - 1

    def can_place_cows(self, stalls: List[int], dist: int, cows: int) -> bool:
        # place first cow at first index
        prev_cow = 0
        cows -= 1
        for i in range(1, len(stalls)):
            if stalls[i] - stalls[prev_cow] >= dist:
                prev_cow = i
                cows -= 1

            if cows <= 0:
                return True

        return False
```

## Optimal Solution, T:O(n log n + n log max(stalls))

```python
class Solution:
    def aggressiveCows(self, stalls: List[int], cows: int) -> int:
        stalls.sort()

        # set the limits
        right = stalls[-1] - stalls[0]
        left = stalls[-1] - stalls[0]
        for i in range(0, len(stalls) - 1):
            # find the minimum gap bw stalls
            diff = stalls[i + 1] - stalls[i]
            left = min(left, diff)
        left = max(left, 1)

        ans = 0
        while left <= right:
            mid = (left + right) >> 1

            if self.can_place_cows(stalls, mid, cows):
                ans = mid
                left = mid + 1
            else:
                right = mid - 1
        return ans

    def can_place_cows(self, stalls: List[int], dist: int, cows: int) -> bool:
        # place first cow at first index
        prev_cow = 0
        cows -= 1
        for i in range(1, len(stalls)):
            if stalls[i] - stalls[prev_cow] >= dist:
                prev_cow = i
                cows -= 1

            if cows <= 0:
                return True

        return False
```
