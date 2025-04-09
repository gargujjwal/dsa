---
id: container-with-most-water
aliases: []
tags: []
layout: default
title: Container with Most Water
---

## Brute Solution T:O(n^2) S:O(1)

```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        max_area = -1
        for i in range(0, len(height)):
            for j in range(i + 1, len(height)):
                dist = j - i
                area = dist * min(height[j], height[i])
                max_area = max(area, max_area)

        return max_area
```

## Optimized Solution T:O(n) S:O(1)

See Trapping Rainwater Solution

```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        max_area = -1

        left = 0
        right = len(height) - 1
        l_max = height[left]
        r_max = height[right]
        while left < right:
            l_max = max(l_max, height[left])
            r_max = max(r_max, height[right])

            dist = right - left
            if l_max < r_max:
                area = dist * l_max
                left += 1
            else:
                area = dist * r_max
                right -= 1

            max_area = max(area, max_area)

        return max_area
```
