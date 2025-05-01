---
id: pascals-triangle
aliases: []
tags: []
layout: default
title: Pascals Triangle
---

# Optimal Solution

```python
from typing import List


class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        ans: List[List[int]] = [[1]]

        if numRows == 1:
            return ans

        numRows -= 1
        ans.append([1, 1])
        numRows -= 1

        while numRows > 0:
            row: List[int] = [1] * (len(ans[-1]) + 1)
            for i in range(1, len(row) - 1):
                row[i] = ans[-1][i - 1] + ans[-1][i]
            ans.append(row)
            numRows -= 1

        return ans
```
