---
id: longest-subarray-with-0-sum
aliases: []
tags: []
layout: default
title: Longest Subarray with 0 Sum
---

## Brute Solution

- Generate all the sub-arrays and find their sum

```python
def maxLen(arr: List[int]) -> int:
    n = len(arr)
    ans = 0

    for i in range(0, n):
        curr_sum = 0
        for j in range(i, n):
            curr_sum += arr[j]

            if curr_sum == 0:
                ans = max(ans, j - i + 1)

    return ans
```

## Optimal Solution

- Observation
  - Take array [15, -2, 2, -8, 1, 7, 10, 23] and look at prefix sum (sum:idx)
    [15:0, 13:1, 15:2, 7:3, 8:4, 15:5, 25:6, 48:7], when prefix-sum repeats you can just subtract
    current index and the index that it occurred previously to get maximum
    subarray

```python
def maxLen(arr: List[int]) -> int:
    n = len(arr)
    # cumulative sum / prefix sum
    cum_sum: Dict[int, int] = dict()

    ans = 0
    curr_sum = 0
    for i in range(0, n):
        curr_sum+= arr[i]

        if curr_sum == 0:
            ans = max(ans, i + 1)

        # check if that sum has been seen before
        if curr_sum in cum_sum:
            ans = max(ans, i - cum_sum[curr_sum])
        else:
            cum_sum[curr_sum] = i

    return ans
```
