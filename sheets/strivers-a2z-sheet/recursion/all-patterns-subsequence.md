---
id: all-patterns-subsequence
aliases: []
tags: []
layout: default
title: All Patterns Subsequence
---

## Printing Subsequence whose sum is k

```java
class Solution {
    public List<List<Integer>> subsequenceWithK(int[] nums, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<>(), 0, 0, nums, k);
        return ans;
    }

    private void helper(List<List<Integer>> ans, List<Integer> currSet, int currSum, int i, int[] nums, int k) {
        if (i >= nums.length) {
            if (currSum == k) {
                ans.add(new ArrayList<>(currSet));
            }
            return;
        }

        // select
        currSet.add(nums[i]);
        currSum += nums[i];
        helper(ans, currSet, currSum, i + 1, nums, k);
        currSum -= nums[i];
        currSet.removeLast();

        // reject
        helper(ans, currSet, currSum, i + 1, nums, k);
    }

    public static void main(String[] args) {
        System.err.println(new Solution().subsequenceWithK(new int[] { 1, 2, 3 }, 3));
        System.err.println(new Solution().subsequenceWithK(new int[] { 1, 2, 3 }, 7));
        System.err.println(new Solution().subsequenceWithK(new int[] { 17, 18, 6, 11, 2, 4 }, 6));
    }
}
```

## Printing Just one subsequence whose sum is k, (the first subsequence)

```java
public class Solution {
    public List<List<Integer>> subsequenceWithK(int[] nums, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<>(), 0, 0, nums, k);
        return ans;
    }

    private boolean helper(List<List<Integer>> ans, List<Integer> currSet, int currSum, int i, int[] nums, int k) {
        if (i >= nums.length) {
            if (currSum == k) {
                ans.add(new ArrayList<>(currSet));
                return true;
            }
            return false;
        }

        // select
        currSet.add(nums[i]);
        currSum += nums[i];
        if (helper(ans, currSet, currSum, i + 1, nums, k)) {
            return true;
        }
        ;
        currSum -= nums[i];
        currSet.removeLast();

        // reject
        if (helper(ans, currSet, currSum, i + 1, nums, k)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.err.println(new Solution().subsequenceWithK(new int[] { 1, 2, 3 }, 3));
        System.err.println(new Solution().subsequenceWithK(new int[] { 1, 2, 3 }, 7));
        System.err.println(new Solution().subsequenceWithK(new int[] { 17, 18, 6, 11, 2, 4 }, 6));
    }
}
```

## Count subsequence

```java

```
