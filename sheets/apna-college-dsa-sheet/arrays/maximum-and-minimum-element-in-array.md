---
id: maximum-and-minimum-element-in-array
aliases: []
tags: []
---

# Maximum & Minimum Element in Array

```python
import sys


class Solution:
    def get_min_max(self, arr: list[int]) -> tuple[int, int]:
        min_el = sys.maxsize
        max_el = -sys.maxsize - 1

        for i in arr:
            min_el = min(i, min_el)
            max_el = max(i, max_el)

        return min_el, max_el
```
