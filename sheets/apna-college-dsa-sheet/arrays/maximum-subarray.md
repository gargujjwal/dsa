---
id: maximum-subarray
aliases: []
tags: []
---

# Maximum Sub-Array

## Brute Solution

```python
import sys
from typing import List


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        return Solution.brute_approach(nums)

    @staticmethod
    def brute_approach(num: List[int]) -> int:
        max_sum = -sys.maxsize - 1

        for i in range(len(num)):
            curr_sum = 0
            for el in num[i:]:
                curr_sum += el
                max_sum = max(curr_sum, max_sum)

        return max_sum
```

## Optimal Solution

```python
import sys
from typing import List


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        return Solution.optimal_approach(nums)

    @staticmethod
    def optimal_approach(num: List[int]) -> int:
        max_sum = -sys.maxsize - 1

        curr_sum = 0
        for el in num:
            curr_sum += el
            max_sum = max(curr_sum, max_sum)
            if curr_sum < 0:
                curr_sum = 0

        return max_sum
```
