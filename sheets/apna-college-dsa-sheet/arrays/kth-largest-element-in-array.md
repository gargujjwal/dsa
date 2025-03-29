---
id: kth-largest-element-in-array
aliases: []
tags: []
---

# Kth Largest Element in Array

## Better

```python
from typing import List


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        nums.sort(reverse=True)
        return nums[k - 1]
```

## Optimal

We use modified version of partitioning algorithm of quicksort called **QuickSelect**.

- Partition the algorithm according to pivot at last index, then swap pivot
  with partition index. Resulting array should have all elements smaller or equal
  to left of partition idx and all elements bigger towards right
- Then check if k exists on right or left of partition idx and decrease
  search space accordingly

> [!Info]
> This works because partition algo partitions the array into smaller and bigger
> array according to pivot. We want kth largest so `n - k`th index, now if
> it exists on right of partition idx then we can be sure that it won't exist
> on left of partition idx, cuz all are smaller than pivot and kth clearly
> is bigger than pivot

```python
from typing import List


class Solution:
    def findKthLargest(self, arr: List[int], k: int) -> int:
        return self._quick_select(arr, k)

    def _quick_select(self, arr: List[int], k: int):
        k = len(arr) - k
        left = 0
        right = len(arr) - 1

        while True:
            partition_idx = self._parition_arr(arr, left, right)

            # swap pvt with partition idx
            arr[partition_idx], arr[right] = arr[right], arr[partition_idx]

            if k > partition_idx:
                left = partition_idx + 1
            elif k < partition_idx:
                right = partition_idx - 1
            else:
                return arr[partition_idx]

    def _parition_arr(self, arr: List[int], start: int, end: int) -> int:
        pvt = arr[end]
        left = start

        for i in range(start, end):
            if arr[i] <= pvt:
                arr[left], arr[i] = arr[i], arr[left]
                left += 1

        return left
```

> [!WARNING]
> It won't work with Hoares' Partition cuz we can't be sure where pivot will
> end up. We want to put the pivot in its sorted position, which can be done
> via lomuto partition
