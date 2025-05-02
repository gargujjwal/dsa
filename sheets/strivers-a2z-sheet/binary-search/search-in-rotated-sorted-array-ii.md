---
id: search-in-rotated-sorted-array-ii
aliases: []
tags: []
layout: default
title: Search in Rotated Sorted Array II
---

## Optimal Solution, Avg: T:O(log n), Worst: T:O(n) (When you have too)

- Time Complexity

  - Average: O(log n)
  - Worst: O(n)
    - When there are too many duplicates the code would iterate near about half of
      array, (the new part), hence linear time complexity

- It's quite similar to Search in rotated sorted array II, we can reuse that solution
- We just have to take care of one case specially which is when
  `arr[left] == arr[mid] == arr[right]`, because then we don't know which part is
  sorted. But we do know that `arr[mid]` ain't our solution so we just trim
  down the search space

```python
def search(arr: List[int], target: int) -> bool:
    left = 0
    right = len(arr) - 1

    while left <= right:
        mid = (left + right) >> 1

        if arr[mid] == target:
            return True

        # new part
        if arr[left] == arr[mid] == arr[right]:
            left += 1
            right -= 1
            continue

        # check which part is sorted
        if arr[left] <= arr[mid]:
            # left part is sorted
            # check if target exists in this subarray
            if target >= arr[left] and target < arr[mid]:
                right = mid - 1
            else:
                # target doesn't exist in left subarray, it might in
                # right subarray
                left = mid + 1
        elif arr[left] > arr[mid]:
            # right part is sorted
            if target > arr[mid] and target <= arr[right]:
                left = mid + 1
            else:
                # target doesn't exist in right subarray, it might in
                # left subarray
                right = mid - 1

    return False
```
