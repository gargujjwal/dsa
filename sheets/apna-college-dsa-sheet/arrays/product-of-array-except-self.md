---
id: product-of-array-except-self
aliases: []
tags: []
layout: default
title: Product of Array Except Self
---

# Product of Array Except Self

## Brute

```python
from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        # n^2
        n = len(nums)
        ans = [1 for _ in nums]
        for i in range(n):
            for j in range(n):
                if i == j:
                    continue
                ans[i] *= nums[j]
        return ans
```

## Better but Not Legal

Uses division

```python
from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        product = 1
        zero_count = 0
        for i in nums:
            if i != 0:
                product *= i
            else:
                zero_count += 1

        ans = [product for _ in nums]
        for i in range(len(ans)):
            if zero_count == 0:
                ans[i] = product // nums[i]
            elif zero_count == 1:
                # only index with 0, will have non-zero products
                if nums[i] != 0:
                    ans[i] = 0
            else:
                ans[i] = 0

        return ans
```

## Better

- We can understand the product required for each index as `left_prefix_prod` and
  `right_prefix_prod`. Now we only have to calculate these two variables and we
  can multiply them to get answer for any index
- Implementation:
  1. We create `left_prod` array which contains left prefix product for each index
     i.
  2. Similarly we crate `right_prod` array by iterating from right
  3. Now simply for each index, we multiply `left_prod[i] * right_prod[i]`

```python
from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)

        # calculate left product prefix
        left_prod: List[int] = [1 for _ in range(n)]
        for i in range(1, n):
            left_prod[i] = left_prod[i - 1] * nums[i - 1]

        # calculate right product prefix
        right_prod: List[int] = [1 for _ in range(n)]
        for i in range(n - 2, -1, -1):
            right_prod[i] = right_prod[i + 1] * nums[i + 1]

        # now calculate ans array
        return [left_prod[i] * right_prod[i] for i in range(n)]
```

## Optimal

- We simply reduce the extra space being used to calculate prefix products, because
  we see that for every index i, `left_prod[i] * right_prod[i]` so, we simply
  calculate both things directly in ans array

```python
from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [1 for _ in range(n)]

        # pass considering left product
        left_prod = nums[0]
        for i in range(1, n - 1):
            ans[i] = left_prod
            left_prod *= nums[i]

        # pass considering right product
        right_prod = nums[-1]
        for i in range(n - 2, 0, -1):
            ans[i] *= right_prod
            right_prod *= nums[i]

        # calculate separately for indices 0, n-1
        ans[0] = right_prod
        ans[-1] = left_prod

        return ans
```
