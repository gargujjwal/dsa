---
id: print-all-combinations-of-array-of-size-n
aliases: []
tags: []
layout: default
title: Print All Combinations of Array of Size N
---

# Required Knowledge

##

```python
def permutations(arr: list[int]) -> list[list[int]]:
    ans: list[list[int]] = list()
    curr_permutation: list[int] = list()
    freq: list[bool] = [False] * len(arr)

    _recursive_permutation(arr, curr_permutation, freq, ans)
    return ans


def _recursive_permutation(
    arr: list[int], curr_permutation: list[int], freq: list[bool], ans: list[list[int]]
) -> None:
    # base case
    if len(curr_permutation) == len(arr):
        ans.append(curr_permutation.copy())
        return

    for i in range(0, len(arr)):
        if not freq[i]:
            # create permutation starting from ith idx
            curr_permutation.append(arr[i])
            freq[i] = True
            _recursive_permutation(arr, curr_permutation, freq, ans)

            freq[i] = False
            curr_permutation.pop()

```
