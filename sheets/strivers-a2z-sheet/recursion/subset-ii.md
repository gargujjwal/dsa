---
id: subset-ii
aliases: []
tags: []
layout: default
title: Subset II
---

## Optimal Solution

```java
class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    helper(0, new ArrayList<>(), ans, nums);
    return ans;
  }

  private void helper(int idx, List<Integer> currAns, List<List<Integer>> ans, int[] arr) {
    ans.add(new ArrayList<>(currAns));
    for (int i = idx; i < arr.length; i++) {
      if (i > idx && arr[i] == arr[i - 1]) continue;

      currAns.add(arr[i]);
      helper(i + 1, currAns, ans, arr);
      currAns.removeLast();
    }
  }
}

```
