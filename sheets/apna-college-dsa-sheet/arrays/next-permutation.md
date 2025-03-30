---
id: next-permutation
aliases: []
tags:
  - permutation
layout: default
title: Next Permutation
---

# Next Permutation

## Brute

Time Complexity: O(n! \* n)

1. Find all permutations in sorted manner
2. Locate current permutation among all permutations
3. Modify current permutation to match the next permutation if it exists otherwise
   sort it

## Optimal

Time Complexity: O(n)

0. Write all permutations of `[1, 2, 3, 4, 5]`, and u will notice that,
   we try to keep the starting array common among permutation to get next permutation
   so `[1, 2, 3, 5, 4]`, `123` stays same.

1. Find the Pivot point:

   - Pivot pt is where `arr[i] < arr[i+1]`. We have to find the last pivot pt
     because we want to keep the prefix array as long as possible
   - Start from the end, and break at first sight of pivt pt
   - **Edge Case:** no pivot pt. In final permutation (ie descending arr) there
     won't be any pivot, pt so return the reversed array as ans

2. Find least maximum of pivot number in `arr[pivot_idx + 1:]` and swap it
   with pivot
3. Sort the array after `pivot_idx`. U can just reverse the array as it already
   is in descending order

```python
from typing import List


class Solution:
    def nextPermutation(self, arr: List[int]) -> None:
        """
        Do not return anything, modify arr in-place instead.
        """
        n = len(arr)
        # find the pivot index, starting from end
        pvt_idx = -1
        for i in range(n - 2, -1, -1):
            if arr[i] < arr[i + 1]:
                pvt_idx = i
                break

        # if no pvt idx is found, then no further permutation exists
        # so just return sorted array which will be reverse of curr arr
        if pvt_idx == -1:
            return self._reverse_arr(arr, 0, n - 1)

        # if found, swap it with least maximum towards right side of it
        for i in range(n - 1, pvt_idx, -1):
            if arr[i] > arr[pvt_idx]:
                arr[i], arr[pvt_idx] = arr[pvt_idx], arr[i]
                break

        # sort the sub-array towards right of pvt idx, which is in descending
        # order, so reverse it
        self._reverse_arr(arr, pvt_idx + 1, n - 1)

    def _reverse_arr(self, arr: List[int], start: int, end: int) -> None:
        """
        Reverses the subarray from index `start` to `end` (inclusive) in place
        """
        while start < end:
            arr[start], arr[end] = arr[end], arr[start]
            start += 1
            end -= 1
```
