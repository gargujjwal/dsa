---
id: kth-element-of-two-arrays
aliases: []
tags: []
layout: default
title: Kth Element of Two Arrays
---

## Better Solution

```python
def kthElement(a: List[int], b: List[int], k: int) -> int:
    n1 = len(a)
    n2 = len(b)
    # kth element is at k-1 index
    k -= 1
    cnt = 0

    i = 0
    j = 0
    while i < n1 and j < n2:
        if a[i] < b[j]:
            if cnt == k:
                return a[i]
            i += 1
        else:
            if cnt == k:
                return b[j]
            j += 1
        cnt += 1

    while i < n1:
        if cnt == k:
            return a[i]
        i += 1
        cnt += 1

    while j < n2:
        if cnt == k:
            return b[j]
        j += 1
        cnt += 1

    return 0
```

## Optimal Solution

```python
def kthElement(a: List[int], b: List[int], k: int) -> int:
    n1 = len(a)
    n2 = len(b)
    if n1 > n2:
        return self.kthElement(b, a, k)

    left = max(0, k - n2)
    right = min(n1, k)

    while left <= right:
        mid1 = (left + right) >> 1
        mid2 = k - mid1

        l1 = -sys.maxsize
        l2 = -sys.maxsize
        r1 = sys.maxsize
        r2 = sys.maxsize

        if mid1 - 1 >= 0:
            l1 = a[mid1 - 1]
        if mid1 < n1:
            r1 = a[mid1]
        if mid2 - 1 >= 0:
            l2 = b[mid2 - 1]
        if mid2 < n2:
            r2 = b[mid2]

        if l1 <= r2 and l2 <= r1:

            return max(l1, l2)
        elif l2 > r1:
            left = mid1 + 1
        else:
            right = mid1 - 1

    return 0
```
