---
id: kokos-eating-bananas
aliases: []
tags: []
layout: default
title: Koko Eating Bananas
---

## Brute Solution, T:O(k n)

```python
def minEatingSpeed(piles: List[int], h: int) -> int:
    k = 1
    while True:
        # time for which guards are gone
        hours = h
        for bananas in piles:
            # time it takes Koko to eat bananas in current pile at the speed
            # of k bananas per hour
            time = ceil(bananas / k)
            hours -= time
            if hours < 0:
                break

        if hours < 0:
            k += 1
            continue

        return k
```

## Optimal Solution, T:O(n log max(n))

- The optimal solution uses Binary Search, to apply binary search we need to
  figure out the limits in which answer can lie. So if I eat the `max(piles)`
  bananas in one hour, it would take me `len(piles)` hours, which is the least
  amount of hours. But we need to minimize speed so Koko can eat minimum of
  1 banana per hour and maximum of `max(piles)` bananas per hour
- Range: `[1, max(piles)]`, so we apply binary search on this range, for every
  mid we check if current speed (=mid) is enough to eat all bananas in required
  time, if it is, then we look towards left half to see if we can do better
  - If current speed is not enough then we look towards right half

```python
def minEatingSpeed(piles: List[int], h: int) -> int:
    ans = 0
    left = 1
    right = max(piles)
    while left <= right:
        mid = (left + right) >> 1

        # calculate time taken by Koko to eat at `mid` bananas/h
        hours = h
        time = 0
        for bananas in piles:
            time = ceil(bananas / mid)
            hours -= time
            if hours < 0:
                break

        # determine if current speed is the minimum speed at which
        # Koko can eat
        if hours >= 0:
            ans = mid
            right = mid - 1
        else:
            left = mid + 1

    return ans
```
