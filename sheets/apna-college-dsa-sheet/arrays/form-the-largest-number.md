---
id: form-the-largest-number
aliases: []
tags: []
layout: default
title: Form the Largest Number
---

## Optimal T:O(n log n) S:O(1)

```python
from functools import cmp_to_key
from typing import List


class Solution:
    def findLargest(self, arr: List[int]) -> str:
        ans =  "".join(sorted(map(str, arr), key=cmp_to_key(Solution._custom_cmp)))
        return "0" if ans[0] == "0" else ans

    @staticmethod
    def _custom_cmp(first: str, sec: str) -> int:
        return -1 if first + sec > sec + first else 1
```
