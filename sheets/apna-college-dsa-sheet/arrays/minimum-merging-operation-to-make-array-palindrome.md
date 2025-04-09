---
id: minimum-merging-operation-to-make-array-palindrome
aliases: []
tags: []
layout: default
title: Minimum No of Merging Operation to make Array Palindrome
---

## Optimal Solution

```python
def min_merge(arr: list[int]) -> int:
    operations = 0

    i = 0
    j = len(arr) - 1
    while i <= j:
        # current indices are in palindrome fashion, check inwards
        if arr[i] == arr[j]:
            i += 1
            j -= 1
        # merge to see if smaller elements can sum up to arr[j]
        elif arr[i] < arr[j]:
            i += 1
            arr[i] = arr[i] + arr[i - 1]
            operations += 1
        # merge to see if smaller elements can sum up to arr[j]
        else:
            j -= 1
            arr[j] = arr[j] + arr[j + 1]
            operations += 1

    return operations
```
