---
id: square-root
aliases: []
tags: []
layout: default
title: Square Root
---

## Optimal Solution

```python
def floorSqrt(n: int) -> int:
    if n < 4:
        return 1
    left = 2
    right = n >> 1
    while left <= right:
        mid = (left + right) >> 1
        square = mid * mid
        if square == n:
            return mid
        elif square < n:
            left = mid + 1
        else:
            right = mid - 1

    return right
```
