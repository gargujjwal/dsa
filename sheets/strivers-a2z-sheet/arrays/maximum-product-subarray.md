---
id: maximum-product-subarray
aliases: []
tags: []
layout: default
title: Maximum Product Subarray
---

## Brute Solution

```python
def maxProduct(nums: List[int]) -> int:
    n = len(nums)

    ans = 0
    for i in range(0, n):
        prod = 1
        for j in range(i, n):
            prod *= nums[j]
            ans = max(ans, prod)

    return ans
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
def maxProduct(nums: List[int]) -> int:
    ans = -maxsize - 1

    prefix = 1
    suffix = 1
    for i in range(len(nums)):
        if prefix == 0:
            prefix = 1
        if suffix == 0:
            suffix = 1

        prefix *= nums[i]
        suffix *= nums[len(nums) - 1 - i]

        ans = max(prefix, suffix, ans)

    return ans
```
