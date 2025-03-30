---
id: repeat-and-missing-number-array
aliases: []
tags: []
layout: default
title: Repeat & Missing Number Array
---

# Repeat & Missing Number Array

## Brute

```python
from typing import Tuple


class Solution:
    def repeatedNumber(self, arr: Tuple[int]) -> Tuple[int, int]:
        # find A
        a = 0
        sum = 0
        hash_set = set()
        for i in arr:
            sum += i
            if i in hash_set:
                a = i
            else:
                hash_set.add(i)

        # find B
        n = len(arr)
        b = ((n * (n + 1)) // 2) - sum + a

        return a, b
```

## Optimal

```python
from typing import Tuple


class Solution:
    def repeatedNumber(self, arr: Tuple[int]) -> Tuple[int, int]:
        n = len(arr)

        natural_sum = (n * (n + 1)) // 2
        sum_of_sq_natural = (n * (n + 1) * (2 * n + 1)) // 6

        actual_sum = 0
        actual_sq_sum = 0
        for i in arr:
            actual_sum += i
            actual_sq_sum += i * i

        a = (
            (actual_sum - natural_sum)
            + ((actual_sq_sum - sum_of_sq_natural) // (actual_sum - natural_sum))
        ) // 2
        b = a - actual_sum + natural_sum
        return a, b
```
