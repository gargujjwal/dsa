---
id: rearrange-array-elements-by-sign
aliases: []
tags: []
layout: default
title: Rearrange Array Elements By Sign
---

## Brute Solution

```python
def rearrangeArray(self, arr: List[int]) -> List[int]:
    n = len(arr)
    neg: List[int] = [0] * (n // 2)
    pos: List[int] = [0] * (n // 2)

    # populate neg & pos arrays
    i = 0
    j = 0
    for val in arr:
        if val > 0:
            pos[j] = val
            j += 1
        else:
            neg[i] = val
            i += 1

    # recreate arr
    i = 0
    j = 0
    k = 0
    pos_turn = True
    while k < n:
        if pos_turn:
            arr[k] = pos[j]
            j += 1
        else:
            arr[k] = neg[i]
            i += 1
        pos_turn = not pos_turn
        k += 1

    return arr
```

## Optimal
