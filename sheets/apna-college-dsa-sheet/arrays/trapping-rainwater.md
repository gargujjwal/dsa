---
id: trapping-rainwater
aliases: []
tags: []
layout: default
title: Trapping Rainwater
---

# Trapping Rainwater

## Brute Solution

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
