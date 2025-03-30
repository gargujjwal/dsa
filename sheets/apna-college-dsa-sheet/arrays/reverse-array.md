---
id: reverse-array
aliases: []
tags: []
layout: default
title: Reverse Array
---

# Reverse Array

```python
class Solution:
    def reverseArray(self, arr: list[int]) -> None:
        n = len(arr)
        for i in range(n // 2):
            arr[i], arr[n - i - 1] = arr[n - i - 1], arr[i]
```
