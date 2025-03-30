---
id: chocolate-distribution-problem
aliases: []
tags: []
layout: default
title: Chocolate Distribution Problem
---

# Chocolate Distribution Problem

- Since it order of packets doesn't matter, we sort the arrays in ascending order
  to know where min and max lies in array.
- Now `m` packets are to be distributed, so we create a **sliding window** of size
  `m` and check for the minimum difference of chocolates in that sliding window
- On each iteration we slide the window by 1 until we reach end of array and
  we finally return the calculated minimum difference

```python
import sys


class Solution:

    def findMinDiff(self, arr: list[int], m: int):
        arr.sort()

        diff = sys.maxsize
        # now using sliding window
        left = 0
        right = m - 1
        while right < len(arr):
            diff = min(diff, arr[right] - arr[left])
            right += 1
            left += 1

        return diff
```
