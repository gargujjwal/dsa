---
id: pair-sum-in-sorted-rotated-array
aliases: []
tags: []
layout: default
title: Pair Sum in Sorted Rotated Array
---

## Optimized, T:O(n log n), S:O(1)

We sort the array and leverage the fact that array is sorted by keeping two ptrs
one on left and other on right extreme, then we try to find `sum = arr[left] + arr[right]`
and if sum is less than target then to increase sum, we increment left ptr.
If sum is greater than target then we decrement right ptr. We do this, until
we find a pair whose sum is equal to target or until left and right ptr meet

```python
class Solution:
    def pairInSortedRotated(self, arr: List[int], target: int) -> bool:
        arr.sort()
        n = len(arr)
        left = 0
        right = n - 1
        while left < right:
            sum = arr[left] + arr[right]
            if sum > target:
                right -= 1
            elif sum < target:
                left += 1
            else:
                return True
        return False
```

## Optimal, T:O(n), S:O(1)

Same as above but we first find pivot then do above algo

```python
class Solution:
    def pairInSortedRotated(self, arr: List[int], target: int) -> bool:
        # find the pivot pt first
        pvt_idx = self._find_pvt_idx(arr)

        n = len(arr)
        # assign both pointers
        if pvt_idx == -1:
            left = 0
            right = n - 1
        else:
            left = pvt_idx
            right = pvt_idx - 1

        while left != right:
            curr_sum = arr[left] + arr[right]

            if curr_sum < target:
                # increment left
                if left + 1 == n:
                    left = 0
                else:
                    left += 1
            elif curr_sum > target:
                # decrement right
                if right - 1 == -1:
                    right = n - 1
                else:
                    right -= 1
            else:
                return True

        return False

    def _find_pvt_idx(self, arr: List[int]) -> int:
        for i in range(0, len(arr) - 1):
            if arr[i] > arr[i + 1]:
                return i + 1

        return -1
```
