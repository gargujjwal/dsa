---
id: combination-sum-iii
aliases: []
tags: []
layout: default
title: Combination Sum III
---

## Optimal Solution

```java
class Solution {
  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> ans = new ArrayList<>();
    helper(1, new ArrayList<>(), n, ans, k);
    return ans;
  }

  private void helper(int dig, List<Integer> currAns, int target, List<List<Integer>> ans, int k) {
    if (target == 0 && k == 0) {
      ans.add(new ArrayList<>(currAns));
      return;
    }

    if (target < 0 || k < 0) {
      return;
    }

    for (int i = dig; i < 10; i++) {
      if (i <= target) {
        currAns.add(i);
        helper(i + 1, currAns, target - i, ans, k - 1);
        currAns.removeLast();
      }
    }
  }
}
```
