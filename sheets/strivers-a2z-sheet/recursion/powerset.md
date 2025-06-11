---
id: powerset
aliases: []
tags: []
layout: default
title: powerset
---

## Solution

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<>(), 0, nums);
        return ans;
    }

    private void helper(List<List<Integer>> ans, List<Integer> currSet, int i, int[] nums) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(currSet));
            return;
        }

        // select
        currSet.add(nums[i]);
        helper(ans, currSet, i + 1, nums);
        currSet.removeLast();

        // reject
        helper(ans, currSet, i + 1, nums);
    }
}
```
