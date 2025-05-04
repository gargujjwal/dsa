---
id: minimum-days-to-make-m-bouquets
aliases: []
tags: []
layout: default
title: Minimum Days to Make m bouquets
---

## Brute Solution T:O(n max(n)) S:O(1)

```python
def minDays(bloom_days: List[int], m: int, k: int) -> int:
    n = len(bloom_days)
    # we don't have enough flowers
    if m * k > n:
        return -1

    # make first flowers bloom
    min_bloom_days = min(bloom_days)
    max_bloom_days = max(bloom_days)
    for i in range(n):
        bloom_days[i] -= min_bloom_days - 1

    # all flowers would have bloomed on `max(bloom_days)`
    for i in range(min_bloom_days, max_bloom_days + 1):
        required_bouquets = m
        # decrement one day from non-bloomed flowers
        for j in range(n):
            if bloom_days[j] > 0:
                bloom_days[j] -= 1

        left = -1
        for right in range(n):
            if bloom_days[right] == 0:
                if left == -1:
                    left = right

                # we can make one bouquet here
                if right - left + 1 == k:
                    required_bouquets -= 1
                    # reset
                    left = -1
            else:
                left = -1

        # check if we have made enough bouquets
        if required_bouquets <= 0:
            return i

    return -1
```

## Better Solution T:O(n log max(n)) S:O(n)

```python
class Solution:
    def minDays(self, bloom_days: List[int], m: int, k: int) -> int:
        # we don't have enough flowers
        if m * k > len(bloom_days):
            return -1

        # make first flowers bloom
        ans = -1
        left = min(bloom_days)
        right = max(bloom_days)
        while left <= right:
            mid = (left + right) >> 1

            # see if i can make enough bouquets
            if self._can_make_bouquets(bloom_days.copy(), mid, m, k):
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans

    def _can_make_bouquets(
        self,
        bloom_days: List[int],
        days_passed: int,
        bouquets_req: int,
        flowers_in_bouquet: int,
    ) -> bool:
        left = -1
        for right in range(len(bloom_days)):
            # bloom the flower if it can
            bloom_days[right] = max(bloom_days[right] - days_passed, 0)

            # check if current flower has bloomed
            if bloom_days[right] == 0:
                # initialize range of bloom flowers
                if left == -1:
                    left = right

                # we can make one bouquet here
                if right - left + 1 == flowers_in_bouquet:
                    bouquets_req -= 1
                    # reset range
                    left = -1
            else:
                # reset range
                left = -1

        # check if we have made enough bouquets
        return bouquets_req <= 0
```

## Optimal Solution, T:O(n log max(n)), S:O(1)

```python
class Solution:
    def minDays(self, bloom_days: List[int], m: int, k: int) -> int:
        # we don't have enough flowers
        if m * k > len(bloom_days):
            return -1

        # make first flowers bloom
        # set the range of binary search [min(bloom_days), max(bloom_days)]
        left = bloom_days[0]
        right = bloom_days[0]
        for i in bloom_days:
            left = min(left, i)
            right = max(right, i)

        ans = -1
        while left <= right:
            mid = (left + right) >> 1

            # see if i can make enough bouquets
            if self._can_make_bouquets(bloom_days, mid, m, k):
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans

    def _can_make_bouquets(
        self,
        bloom_days: List[int],
        days_passed: int,
        bouquets_req: int,
        flowers_in_bouquet: int,
    ) -> bool:
        bouquets_made = 0
        bloomed_cnt = 0
        for i in range(len(bloom_days)):
            if max(bloom_days[i] - days_passed, 0) == 0:
                bloomed_cnt += 1
            else:
                bouquets_made += bloomed_cnt // flowers_in_bouquet
                bloomed_cnt = 0

        # for flowers situated at the end
        bouquets_made += bloomed_cnt // flowers_in_bouquet
        return bouquets_made >= bouquets_req
```
