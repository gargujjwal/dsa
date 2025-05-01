---
id: longest-subarray-with-sum-divisible-by-k
aliases: []
tags: []
layout: default
title: Longest Subarray with Sum Divisible by K
---

## Brute Solution T:O(n^2) S:O(1)

```python
class Solution:
    def longestSubarrayDivK(self, arr: List[int], k: int) -> int:
        max_subarray = 0
        for i in range(0, len(arr)):
            curr_sum = 0
            for j in range(i, len(arr)):
                curr_sum += arr[j]
                if curr_sum % k == 0:
                    max_subarray = max(max_subarray, j - i + 1)

        return max_subarray

```

## Optimized Solution T:O(n) S:O(min(n, k))

### Observations

- for array 5, 2, 1, 4, 7, 8, 2, 5, and k=3 we can write remainders of cumulative sum
  and represent them as shown in the table.
- Notice when we find the same remainder we can just subtract their representation
  and we will get a multiple of 3, ex: 3x2+2 - 3x1+2 = 3, which is 2 + 1.
- This will be the logic of our code, too
- For -ve cum sum, the remainders can be -ve, we can write them in similar
  fashion with +ve remainders, for which we can just add k to it

  | Array     |   5   |   2   |   1   |  4  |   7   |  8  |   2   |   5    |
  | --------- | :---: | :---: | :---: | :-: | :---: | :-: | :---: | :----: |
  | Cum Sum   |   5   |   7   |   8   | 12  |  19   | 27  |  29   |   34   |
  | Remainder |   2   |   1   |   2   |  0  |   1   |  0  |   2   |   1    |
  |           | 3x1+2 | 3x2+1 | 3x2+2 | 3x4 | 3x6+1 | 3x9 | 3x9+2 | 3x11+1 |

### Explanation

- We create a hashmap of remainders and as we iterate through the array
  we calculate the cumulative sum and remainder for it (if remainder < 0, then we
  increment it by k)
- If remainder is 0, then max length will from starting of the array
- If remainder is found in the hashmap, then we can calculate the length as
  `i - hashmap[remainder]`
- If remainder is not found, then we set the key as remainder and val as i

```python
def longestSubarrayDivK(self, arr: List[int], k: int) -> int:
    max_len = 0
    remainder_map: Dict[int, int] = {0: -1}

    curr_sum = 0
    for i, val in enumerate(arr):
        curr_sum += val
        curr_rem = curr_sum % k

        # if remainder gets negative then make it positive
        if curr_rem < 0:
            curr_rem += k

        # find if any other subarray had the same remainder
        if curr_rem in remainder_map:
            max_len = max(max_len, i - remainder_map[curr_rem])
        else:
            remainder_map[curr_rem] = i

    return max_len
```
