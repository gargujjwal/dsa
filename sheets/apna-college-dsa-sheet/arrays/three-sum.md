---
id: three-sum
aliases: []
tags: []
layout: default
title: Three Sum
---

## Brute

```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        ans: Set[Tuple[int]] = set()

        n = len(nums)
        for i in range(0, n):
            for j in range(i + 1, n):
                for k in range(j + 1, n):
                    curr_sum = nums[i] + nums[j] + nums[k]
                    if curr_sum == 0:
                        ans.add(tuple(sorted([nums[j], nums[i], nums[k]])))

        return list(ans)

```

## Optimal

We can convert this problem into two-sum problem if we fix first digit, so we fix
the first digit. Now we sort the array first, we can complete two-sum problem in
O(n) time, so first we sort the array and then fix the digit then use two-sum
logic to find the answer

- Now we don't want to use `HashSet` or such data structure which will increase time
  complexity. So we try to skip duplicates for each pointer, for `i`, `left`, `right`

```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        ans: List[List[int]] = list()
        nums.sort()

        n = len(nums)
        # fix first element
        i = 0
        while i < n - 2:
            # two sum approach here
            left = i + 1
            right = n - 1
            while left < right:
                curr_sum = nums[i] + nums[left] + nums[right]

                if curr_sum > 0:
                    right -= 1
                elif curr_sum < 0:
                    left += 1
                else:
                    ans.append((nums[i], nums[left], nums[right]))
                    left += 1
                    right -= 1

                    # increment left until a different value is found
                    while left < right and nums[left] == nums[left - 1]:
                        left += 1

                    # increment right until a different value is found
                    while right > left and nums[right] == nums[right + 1]:
                        right -= 1



            # increment i until a different value is found
            i += 1
            while i < n and nums[i] == nums[i - 1]:
                i += 1

        return ans
```
