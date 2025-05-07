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
    def aggressiveCows(self, stalls: List[int], k: int) -> int:
        if k > len(stalls):
            return -1
        # sort stalls to keep calc of consecutive distance easy,
        # also doing this i only have to worry about consecutive cows, cuz
        # non-consecutive will be much further apart
        stalls.sort()

        # configure limits for binary search
        # find the min dist and max dist between stalls
        min_dist = maxsize
        for i in range(1, len(stalls)):
            dist = stalls[i] - stalls[i - 1]
            min_dist = min(min_dist, dist)

        left = min_dist
        # max distance would be diff bw most further stall and first stall
        # in which I would only be able to house 2 cows
        right = stalls[-1] - stalls[0]
        ans = min_dist
        while left <= right:
            mid = (left + right) >> 1

            if self._can_settle_cows(stalls, k, mid):
                ans = mid
                left = mid + 1
            else:
                right = mid - 1

        return ans

    def _can_settle_cows(self, stalls: List[int], cows: int, min_dist: int) -> int:
        # assign one cow to first stall
        cows -= 1
        prev_cow = stalls[0]

        for i in range(1, len(stalls)):
            if cows <= 0:
                return True
            # dist bw two cows should be >= min_dist
            if stalls[i] - prev_cow >= min_dist:
                prev_cow = stalls[i]
                cows -= 1

        return False
```
