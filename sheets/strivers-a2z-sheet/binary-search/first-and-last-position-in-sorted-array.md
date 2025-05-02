---
id: first-and-last-position-in-sorted-array
aliases: []
tags: []
layout: default
title: First and Last Position in Sorted Array
---

```java
class Solution {
  public int[] searchRange(int[] arr, int target) {
    var result = new int[2];
    result[0] = getFirstOccur(arr, target);
    result[1] = getLastOccur(arr, target);

    return result;
  }

  private int getFirstOccur(int[] arr, int target) {
    int occur = -1;
    int low = 0;
    int high = arr.length - 1;
    while (low <= high) {
      int mid = low + ((high - low) >> 1);

      if (arr[mid] == target) {
        occur = mid;
        high = mid - 1;
      } else if (arr[mid] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return occur;
  }

  private int getLastOccur(int[] arr, int target) {
    int occur = -1;
    int low = 0;
    int high = arr.length - 1;
    while (low <= high) {
      int mid = low + ((high - low) >> 1);

      if (arr[mid] == target) {
        occur = mid;
        low = mid + 1;
      } else if (arr[mid] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return occur;
  }
}
```

```python
class Solution:
    def searchRange(self, arr: List[int], target: int) -> List[int]:
        if len(arr) == 0:
            return [-1, -1]

        ans: List[int] = [0, 0]
        prev_smaller_el_idx = Solution._previous_smaller_element(arr, target)
        if prev_smaller_el_idx == -1 or arr[prev_smaller_el_idx] != target:
            ans[0] = -1
        else:
            ans[0] = prev_smaller_el_idx

        nxt_greater_el_idx = Solution._next_greater_element(arr, target)
        if nxt_greater_el_idx == -1 or arr[nxt_greater_el_idx] != target:
            ans[1] = -1
        else:
            ans[1] = nxt_greater_el_idx

        return sorted(ans)

    @staticmethod
    def _previous_smaller_element(arr: List[int], x: int) -> int:
        if arr[0] > x:
            return -1

        left = 0
        right = len(arr) - 1

        ans = -1
        while left <= right:
            mid = (left + right) >> 1

            if arr[mid] <= x:
                ans = mid
                left = mid + 1
            else:
                right = mid - 1

        return ans

    @staticmethod
    def _next_greater_element(arr: List[int], target: int) -> int:
        left = 0
        right = len(arr) - 1

        ans = -1
        while left <= right:
            mid = (left + right) >> 1

            if arr[mid] >= target:
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans
```
