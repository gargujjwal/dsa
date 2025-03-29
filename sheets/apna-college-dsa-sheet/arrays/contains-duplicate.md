---
id: contains-duplicate
aliases: []
tags: []
---

# Contains Duplicated

## Brute Solution

- N2 approach

```python
from typing import List


class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        for idx, el in enumerate(nums):
            for i in range(len(nums)):
                if i != idx and nums[i] == el:
                    return True

        return False
```

## Better

1. By sorting the array first, makes the solution O(nlogn)

   ```python
   from typing import List


   class Solution:
       def containsDuplicate(self, nums: List[int]) -> bool:
           # sort the array first, to group the elements
           nums.sort()

           prev_el = nums[0]
           for i in nums[1:]:
               if i == prev_el:
                   return True
               prev_el = i
           return False
   ```

2. Calculate the frequency

   ```python
   from typing import List


   class Solution:
       def containsDuplicate(self, nums: List[int]) -> bool:
           # statistics vala mode
           mode_dict = dict()
           for el in nums:
               if el in mode_dict:
                   mode_dict[el] += 1
               else:
                   mode_dict[el] = 1

           for val in mode_dict.values():
               if val > 1:
                   return True
           return False
   ```

## Optimal

Uses a hashset

```python
from typing import List


class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        unique = set()
        for el in nums:
            if el in unique:
                return True
            else:
                unique.add(el)
        return False
```
