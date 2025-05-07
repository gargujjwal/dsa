---
id: median-of-two-sorted-arrays
aliases: []
tags: []
layout: default
title: Median of Two Sorted Arrays
---

## Brute Solution, T:O(n) S:O(n)

```java
class Solution {
    public static double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = mergeArray(arr1, arr2);

        // even case
        if (merged.length % 2 == 0) return {
            (merged[merged.length / 2 - 1] + (double) merged[merged.length / 2]) / 2;
        }
        // odd case
        return merged[(merged.length + 1) / 2 - 1];
    }

    // merge two sorted arrays
    private static int[] mergeArray(int[] arr1, int[] arr2) {
        int[] newArr = new int[arr2.length + arr1.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length)
            if (arr1[i] < arr2[j]) newArr[k++] = arr1[i++];
            else newArr[k++] = arr2[j++];

        while (i < arr1.length) newArr[k++] = arr1[i++];
        while (j < arr2.length) newArr[k++] = arr2[j++];
        return newArr;
    }
}
```

## Better Solution, T:O(n) S:O(1)

```python
def findMedianSortedArrays(arr1: List[int], arr2: List[int]) -> float:
    n1 = len(arr1)
    n2 = len(arr2)
    n = n1 + n2

    idx2 = n // 2
    idx1 = idx2 - 1
    idx1_el = sys.maxsize
    idx2_el = sys.maxsize

    cnt = 0
    i = 0
    j = 0
    while i < n1 and j < n2:
        if arr1[i] < arr2[j]:
            if cnt == idx1:
                idx1_el = arr1[i]
            if cnt == idx2:
                idx2_el = arr1[i]
            i += 1
        else:
            if cnt == idx1:
                idx1_el = arr2[j]
            if cnt == idx2:
                idx2_el = arr2[j]
            j += 1
        cnt += 1

    while i < n1:
        if cnt == idx1:
            idx1_el = arr1[i]
        if cnt == idx2:
            idx2_el = arr1[i]

        i += 1
        cnt += 1

    while j < n2:
        if cnt == idx1:
            idx1_el = arr2[j]
        if cnt == idx2:
            idx2_el = arr2[j]

        j += 1
        cnt += 1

    if n % 2 == 1:
        return idx2_el

    return (idx1_el + idx2_el) / 2.0
```

## Optimal Solution

```python
def findMedianSortedArrays(arr1: List[int], arr2: List[int]) -> float:
    n1 = len(arr1)
    n2 = len(arr2)
    if n1 > n2:
        return findMedianSortedArrays(arr2, arr1)

    n = n1 + n2
    left_elements = (n + 1) // 2
    low = 0
    high = n1
    while low <= high:
        mid1 = (low + high) >> 1
        mid2 = left_elements - mid1
        l1 = -sys.maxsize - 1
        l2 = -sys.maxsize - 1
        r1 = sys.maxsize
        r2 = sys.maxsize

        if mid1 < n1:
            r1 = arr1[mid1]
        if mid2 < n2:
            r2 = arr2[mid2]
        if mid1 - 1 >= 0:
            l1 = arr1[mid1 - 1]
        if mid2 - 1 >= 0:
            l2 = arr2[mid2 - 1]

        if l1 <= r2 and l2 <= r1:
            if n % 2 == 1:
                return max(l1, l2)
            else:
                return (max(l1, l2) + min(r1, r2)) / 2
        elif l1 > r2:
            high = mid1 - 1
        else:
            low = mid1 + 1

    return 0
```
