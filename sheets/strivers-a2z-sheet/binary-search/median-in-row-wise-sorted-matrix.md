---
id: median-in-row-wise-sorted-matrix
aliases: []
tags: []
layout: default
title: Median in Row-wise Sorted Matrix
---

## Brute Solution

```python
def create_array_from_matrix(mat: list[list[int]]) -> list[int]:
    arr = [0] * (len(mat) * len(mat[0]))
    i = 0
    for row in mat:
        for k in row:
            arr[i] = k
            i += 1

    return arr


def median(mat: list[list[int]], m: int, n: int) -> int:
    arr = create_array_from_matrix(mat)
    # sort the array
    arr.sort()
    # all arrays will be odd as stated by the question
    return arr[len(arr) // 2]
```

## Optimal Solution

```java
public final class Solution {
    public static int findMedian(int mat[][], int m, int n) {
        int reqNoOfSmallerElements = (n * m) / 2;
        int left = minOfMatrix(mat);
        int right = maxOfMatrix(mat);
        while (left <= right) {
            int mid = (left + right) / 2;

            int noOfSmallerElements = getNoOfSmallerElementsInMatrix(mat, mid);
            if (noOfSmallerElements <= reqNoOfSmallerElements) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static int getNoOfSmallerElementsInMatrix(int[][] mat, int target) {
        int ans = 0;
        for (int[] row : mat) {
            ans += upperBound(row, target);
        }

        return ans;
    }

    private static int upperBound(int[] arr, int target) {
        int ans = arr.length;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private static int minOfMatrix(int[][] mat) {
        int ans = Integer.MAX_VALUE;
        // check in the first column
        for (int row = 0; row < mat.length; row++) {
            ans = Math.min(ans, mat[row][0]);
        }

        return ans;
    }

    private static int maxOfMatrix(int[][] mat) {
        int m = mat[0].length;
        int ans = Integer.MIN_VALUE;
        // check in the last column
        for (int row = 0; row < mat.length; row++) {
            ans = Math.max(ans, mat[row][m - 1]);
        }

        return ans;
    }
}
```
