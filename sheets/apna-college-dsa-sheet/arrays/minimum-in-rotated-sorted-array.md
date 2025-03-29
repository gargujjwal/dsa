---
id: maximum-product-subarray
aliases: []
tags: []
layout: default
title: Minimum in Rotated Sorted Array
---

## Brute Solution T:O(n^2), S:O(n)

```python
from sys import maxsize
from typing import List


class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        max_product = -maxsize - 1
        for i in range(len(nums)):
            prod = nums[i]

            for j in range(i + 1, len(nums)):
                max_product = max(max_product, prod)
                prod *= nums[j]

            max_product = max(max_product, prod)

        return max_product
```

## Optimal Solution T:O(n), S:O(1)

This is observational approach, assume if all numbers in array were

- **+ve**: then maximum product would be product of all sub-arrays
- **even -ve**: same as above
- **odd -ve**: then we need to check product of two subarrays, ie, either side
  of every negative number
- **with zero**: simply ignore the zeroes

Below code does the same, as array iterates it calculates prefix and suffix,
and checks which is bigger

```python
from typing import List
from sys import maxsize


class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        max_prod = -maxsize - 1

        prefix = 1
        suffix = 1
        for i in range(len(nums)):
            if prefix == 0:
                prefix = 1
            if suffix == 0:
                suffix = 1

            prefix *= nums[i]
            suffix *= nums[len(nums) - 1 - i]

            max_prod = max(prefix, suffix, max_prod)

        return max_prod
```
