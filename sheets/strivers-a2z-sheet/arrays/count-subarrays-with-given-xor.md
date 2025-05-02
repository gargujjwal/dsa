---
id: count-subarrays-with-given-xor
aliases: []
tags: []
layout: default
title: Count Subarrays With Given Xor
---

## Brute Solution

```python
def subarrayXor(arr: List[int], k: int) -> int:
    n = len(arr)

    ans = 0
    for i in range(0, n):
        xor = 0
        for j in range(i, n):
            xor ^= arr[j]

            if xor == k:
                ans += 1

    return ans
```

## Optimal Solution

- Observation
  - Say for array [4, 2, 2, 6, 4] we have cumulative xor as
    [4:0, 6:1, 4:2, 2:3, 6:4]. Now assume that there exists a subarray with xor
    `k` for index 3, so that would mean that for some number, `x`, we can write
    `x ^ k = 2`, `x = 2 ^ k (xor k both sides)`. Now if `x` exists in cumulative
    xor, then we know that subarray with xor k also exists (quite similar to longest
    sum subarray)
- Code
  - In the code we create a hashmap of cumulative xor (prefix xor) and their count
    since we have to return count not longest subarray

```python
def subarrayXor(arr: List[int], k: int) -> int:
    n = len(arr)

    cum_xor: Dict[int, int] = {0:1}
    ans = 0

    curr_xor = 0
    for i in range(0, n):
        curr_xor ^= arr[i]

        req_xor = curr_xor ^ k
        if req_xor in cum_xor:
            ans += cum_xor[req_xor]

        # add the current xor in hash-map incrementing it every time
        if curr_xor in cum_xor:
            cum_xor[curr_xor] += 1
        else:
            cum_xor[curr_xor] = 1

    return ans
```
