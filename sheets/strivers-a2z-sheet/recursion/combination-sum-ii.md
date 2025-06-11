---
id: combination-sum-ii
aliases: []
tags: []
layout: default
title: Combination Sum II
---

## Brute Solution

```java
class Solution {

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Set<List<Integer>> ans = new HashSet<>();
    helper(0, target, new ArrayList<>(), ans, candidates);
    return ans.stream().toList();
  }

  private void helper(int i, int target, List<Integer> currAns, Set<List<Integer>> ans, int[] arr) {
    if (i == arr.length) {
      if (target == 0) {
        ans.add(new ArrayList<>(currAns.stream().sorted().toList()));
      }
      return;
    }

    // select
    if (arr[i] <= target) {
      currAns.add(arr[i]);
      helper(i + 1, target - arr[i], currAns, ans, arr);
      currAns.removeLast();
    }

    helper(i + 1, target, currAns, ans, arr);
  }
}
```

## Optimal Solution

```java
class Solution {

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);

    List<List<Integer>> ans = new ArrayList<>();
    helper(0, target, new ArrayList<>(), ans, candidates);
    return ans.stream().toList();
  }

  private void helper(
      int idx, int target, List<Integer> currAns, List<List<Integer>> ans, int[] arr) {
    if (target == 0) {
      ans.add(new ArrayList<>(currAns));
      return;
    }

    for (int i = idx; i < arr.length; i++) {
      if (i > idx && arr[i] == arr[i - 1]) continue;
      if (arr[i] > target) break;

      currAns.add(arr[i]);
      helper(i + 1, target - arr[i], currAns, ans, arr);
      currAns.removeLast();
    }
  }
}
```

## Optimal Solution \#Iterative

```java
class Solution {
  public ArrayList<Integer> subsetSums(int[] arr) {
    return iterativeHelper(arr);
  }

  private void helper(int idx, int sum, List<Integer> ans, int[] arr) {
    if (idx == arr.length) {
      ans.add(sum);
      return;
    }

    // select
    helper(idx + 1, sum + arr[idx], ans, arr);
    // reject
    helper(idx + 1, sum, ans, arr);
  }

  private ArrayList<Integer> iterativeHelper(int[] arr) {
    final int MAX_SUBSEQUENCES = 1 << (arr.length);
    ArrayList<Integer> ans = new ArrayList<>(MAX_SUBSEQUENCES);

    for (int i = 0; i < MAX_SUBSEQUENCES; i++) {
      int length = i;
      int sum = 0;
      for (int j = 0; j < arr.length && length > 0; j++, length = length >> 1) {
        if ((length & 1) != 0) {
          sum += arr[j];
        }
      }

      ans.add(sum);
    }

    return ans;
  }
}
```
