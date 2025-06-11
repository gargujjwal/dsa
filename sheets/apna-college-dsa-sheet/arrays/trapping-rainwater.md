---
id: trapping-rainwater
aliases: []
tags: []
layout: default
title: Trapping Rainwater
---

## Brute Solution T:O(n^2), S:O(1)

This solution is thought by me, but should not be used as this is unnecessary
complex solution

- In this approach: Amount of water collected is thought as total free area bw
  two towers. Also, we take the lowest height of the tower and calculate area as
  **min(height of tower 1, height of tower 2) \* distance apart - filled spaces**
  - So for every iteration we try to find a tower which is just bigger or equal
    to current tower towards right, if we find it, then we increase trapped_water
    by net area covered as aforementioned
  - If we fail to find lowest minimum then we try to find a tower which is
    just smaller than the current tower. Now we calculate net area just as before
  - After this, we start next iteration from the tower found at right
- I think this solution is `O(n)` on average case although i am not sure

```python
import sys
from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        trapped_water = 0

        left = 0
        while left < n:
            if height[left] == 0:
                left += 1
                continue

            # first find the height which is just bigger than left height
            least_max_height = sys.maxsize
            right = left + 1
            filled_spaces = 0
            while right < n:
                if height[right] >= height[left] and height[right] < least_max_height:
                    least_max_height = height[right]
                    break
                filled_spaces += height[right]
                right += 1

            if least_max_height != sys.maxsize:
                trapped_water += (
                    (height[left] * (right - left + 1))
                    - filled_spaces
                    - (2 * height[left])
                )
                left = right
                continue

            # find the greatest minimum
            greatest_min = -1
            greatest_min_idx = -1
            filled_spaces = 0
            curr_filled_spaces = 0
            for right in range(left + 1, n):
                curr_filled_spaces += height[right]
                if height[right] < height[left] and height[right] >= greatest_min:
                    greatest_min = height[right]
                    greatest_min_idx = right

                    # increment filled spaces and reset curr_filled_spaces
                    filled_spaces += curr_filled_spaces
                    curr_filled_spaces = 0

            if greatest_min_idx == -1:
                break

            trapped_water += (
                greatest_min * (greatest_min_idx - left + 1)
                - filled_spaces
                - greatest_min
            )
            left = greatest_min_idx

        return trapped_water
```

## Better Solution T:O(n), S:O(2n)

- In this approach we define the water collecting on an idx basis, meaning
  we calculate the water that will be collected over a single tower, add it up
  and present the solution
- We calculate the water on a tower by considering tower of maximum height on
  either side, i.e., for idx i, the water collected on that space would be
  **min(tower of maximum height towards its left side, tower of maximum height
  towards its right side) - height[i]**
- We can calculate the left maximum tower for each index and store it in array
  and do the same for right maximum tower
- Now just iterate over each tower and calc the water and return ans

```python
from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        trapped_water = 0

        # calculate left prefix maximum
        left_prefix_max = [0] * n
        left_prefix_max[0] = height[0]
        for i in range(1, n):
            left_prefix_max[i] = max(left_prefix_max[i - 1], height[i])

        # calculate right prefix maximum
        right_prefix_max = [0] * n
        right_prefix_max[-1] = height[-1]
        for i in range(n - 2, -1, -1):
            right_prefix_max[i] = max(right_prefix_max[i + 1], height[i])

        # now using both these calculate trapped water
        for i in range(n):
            trapped_water += min(left_prefix_max[i], right_prefix_max[i]) - height[i]

        return trapped_water
```

## Even Better Solution T:O(n), S:O(n)

- Building on previous solution, we don't need two arrays as we can calculate
  `left_prefix_max` as we iterate over the array so now we use only **O(n)** space
  instead of **O(2n)** space

```python
from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        trapped_water = 0

        # calculate right prefix maximum
        right_prefix_max = [0] * n
        right_prefix_max[-1] = height[-1]
        for i in range(n - 2, -1, -1):
            right_prefix_max[i] = max(right_prefix_max[i + 1], height[i])

        # calculate left prefix maximum as u iterate
        left_prefix_max = height[0]
        for i in range(n):
            left_prefix_max = max(left_prefix_max, height[i])
            trapped_water += min(left_prefix_max, right_prefix_max[i]) - height[i]

        return trapped_water
```

## Optimal Solution T:O(n), S:O(1)

Building on previous solution we use two pointer approach to reduce space complexity

- Keeping pointers on either sides (`left`, `right`) we can keep track of tower
  of maximum height on both sides. Now for index we can calculate water trapped by
  which-ever side as lowest maximum - height\[i\].
- In this approach we always make sure that we process which ever side is smaller
  so if left_max is smaller, we process left idx and vice versa if right_max is
  smaller, we process right idx

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        trapped_water = 0
        left = 0
        right = len(height) - 1
        l_max = height[left]
        r_max = height[right]
        while left < right:
            l_max = max(l_max, height[left])
            r_max = max(r_max, height[right])

            if l_max < r_max:
                trapped_water += l_max - height[left]
                left += 1
            else:
                trapped_water += r_max - height[right]
                right -= 1

        return trapped_water
```
