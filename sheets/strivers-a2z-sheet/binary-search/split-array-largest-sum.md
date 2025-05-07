---
id: split-array-largest-sum
aliases: []
tags: []
layout: default
title: Split Array Largest Sum
---

## Brute Solution

```python
class Solution:
    def splitArray(self, arr: List[int], k: int) -> int:
        # some testcases whose answer are obvious
        if k > len(arr):
            return -1
        elif k == len(arr):
            return max(arr)
        elif k == 1:
            return sum(arr)

        # find the limits
        left = max(arr)
        right = sum(arr)

        for i in range(left, right + 1):
            if self.count_subarrays(arr, i, k):
                return i

        return -1

    def count_subarrays(
        self, arr: List[int], max_sum: int, max_subarrays_cnt: int
    ) -> bool:
        prev_subarray_sum = 0
        subarray_cnt = 0
        for i in arr:
            if prev_subarray_sum + i <= max_sum:
                prev_subarray_sum += i
            else:
                subarray_cnt += 1
                prev_subarray_sum = i

        if subarray_cnt >= max_subarrays_cnt:
            return False

        subarray_cnt += max_subarrays_cnt - subarray_cnt

        return subarray_cnt == max_subarrays_cnt

```

## Optimal Solution

```python
class Solution:
    def splitArray(self, arr: List[int], k: int) -> int:
        # some testcases whose answer are obvious
        if k > len(arr):
            return -1
        elif k == len(arr):
            return max(arr)
        elif k == 1:
            return sum(arr)

        # find the limits
        ans = 0
        left = max(arr)
        right = sum(arr)

        while left <= right:
            mid = (left + right) >> 1

            if self.cmp_subarrays_cnt(arr, mid, k) > 0:
                left = mid + 1
            else:
                ans = mid
                right = mid - 1

        return ans

    # 0 if exactly max_subarrays_cnt subarrays can be made
    # 1 if more subarrays are made
    # -1 if less subarrays are made
    def cmp_subarrays_cnt(
        self, arr: List[int], max_sum: int, max_subarrays_cnt: int
    ) -> int:
        prev_subarray_sum = 0
        subarray_cnt = 0
        for i in arr:
            if prev_subarray_sum + i <= max_sum:
                prev_subarray_sum += i
            else:
                subarray_cnt += 1
                prev_subarray_sum = i

        if subarray_cnt >= max_subarrays_cnt:
            return 1

        subarray_cnt += max_subarrays_cnt - subarray_cnt

        if subarray_cnt == max_subarrays_cnt:
            return 0
        else:
            return -1
```

## Similar Problem - Painter's Partition

```python
class Solution:
    def minTime (self, arr, k):
        return self.splitArray(arr, k)

    def splitArray(self, arr: List[int], k: int) -> int:
        # some testcases whose answer are obvious
        if k >= len(arr):
            return max(arr)
        elif k == 1:
            return sum(arr)

        # find the limits
        ans = 0
        left = max(arr)
        right = sum(arr)

        while left <= right:
            mid = (left + right) >> 1

            if self.cmp_subarrays_cnt(arr, mid, k) > 0:
                left = mid + 1
            else:
                ans = mid
                right = mid - 1

        return ans

    # 0 if exactly max_subarrays_cnt subarrays can be made
    # 1 if more subarrays are made
    # -1 if less subarrays are made
    def cmp_subarrays_cnt(
        self, arr: List[int], max_sum: int, max_subarrays_cnt: int
    ) -> int:
        prev_subarray_sum = 0
        subarray_cnt = 0
        for i in arr:
            if prev_subarray_sum + i <= max_sum:
                prev_subarray_sum += i
            else:
                subarray_cnt += 1
                prev_subarray_sum = i

        if subarray_cnt >= max_subarrays_cnt:
            return 1

        subarray_cnt += max_subarrays_cnt - subarray_cnt

        if subarray_cnt == max_subarrays_cnt:
            return 0
        else:
            return -1
```
