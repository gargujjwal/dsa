---
id: nth-root
aliases: []
tags: []
layout: default
title: Nth Root
---

## Brute Solution, T:O(n log n)

```python
def NthRoot(n: int, m: int) -> int:
    for i in range(1, m):
        exp = pow(i, n)
        if exp == m:
            return i
        elif exp > m:
            break
    return -1
```

## Optimal Solution

- Refer `sqrt` solution

  ```python
  def NthRoot(n: int, m: int) -> int:
      left = 1
      right = m
      while left <= right:
          mid = (left + right) >> 1
          # exponent
          exp = pow(mid, n)
          if exp == m:
              return mid
          elif exp > m:
              right = mid - 1
          else:
              left = mid + 1

      return -1
  ```

- Since I am using python, below approach isn't necessary but languages with
  datatypes having finite storage like 64 bits or 32 bits, it won't work as
  `pow(mid, n)` might overflow for large numbers
- To remedy this, we only need to compare if mid when multiplied n times
  with `m`, but for that we need not calculate it fully so, I created a new function
  `cmp_nth_pow_of_mid_with_m` which just compares and tells me what i need to
  know

  ```python
  # returns -1 if mid ^ n < m
  # returns  0 if mid ^ n == m
  # returns  1 if mid ^ n > m
  def cmp_nth_pow_of_mid_with_m(mid: int, n: int, m: int) -> int:
      exp = 1
      for i in range(n):
          exp *= mid
          if exp == m:
              return 0
          if exp > m:
              return 1

      return -1

  def NthRoot(n: int, m: int) -> int:
      left = 1
      right = m
      while left <= right:
          mid = (left + right) >> 1
          # exponent
          exp = cmp_nth_pow_of_mid_with_m(mid, n, m)
          if exp == 0:
              return mid
          elif exp == 1:
              right = mid - 1
          else:
              left = mid + 1

      return -1
  ```
