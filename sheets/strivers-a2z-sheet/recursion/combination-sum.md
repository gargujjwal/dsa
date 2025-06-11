---
id: combination-sum
aliases: []
tags: []
layout: default
title: Combination Sum
---

## Brute Solution

```java
class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Set<List<Integer>> result = new HashSet<>();
    helper(candidates, target, result, new ArrayList<>(), 0, 0);
    return result.stream().toList();
  }

  private void helper(
      int[] arr, int target, Set<List<Integer>> ans, List<Integer> currArr, int currSum, int i) {
    // base case
    if (i == arr.length || currSum >= target) {
      if (currSum == target) {
        ans.add(new ArrayList<>(currArr));
      }
      return;
    }

    // keep selecting curr i
    currSum += arr[i];
    currArr.add(arr[i]);
    helper(arr, target, ans, currArr, currSum, i);
    currArr.removeLast();
    currSum -= arr[i];

    // select next i
    currSum += arr[i];
    currArr.add(arr[i]);
    helper(arr, target, ans, currArr, currSum, i + 1);
    currArr.removeLast();
    currSum -= arr[i];

    // reject
    helper(arr, target, ans, currArr, currSum, i + 1);
  }
}
```

## Better Solution

```java
class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    // sort candidates
    Arrays.sort(candidates);

    List<List<Integer>> result = new ArrayList<>();
    helper(candidates, target, result, new ArrayList<>(), 0, 0);
    return result.stream().toList();
  }

  private void helper(
      int[] arr, int target, List<List<Integer>> ans, List<Integer> currArr, int currSum, int i) {
    // base case
    if (i == arr.length || currSum >= target) {
      if (currSum == target) {
        ans.add(new ArrayList<>(currArr));
      }
      return;
    }

    // keep selecting curr i
    currSum += arr[i];
    currArr.add(arr[i]);
    helper(arr, target, ans, currArr, currSum, i);
    currArr.removeLast();
    currSum -= arr[i];

    // reject
    helper(arr, target, ans, currArr, currSum, i + 1);
  }
}
```

## Optimal Solution

```java
class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    helper(candidates, target, result, new ArrayList<>(), 0);
    return result.stream().toList();
  }

  private void helper(
      int[] arr, int target, List<List<Integer>> ans, List<Integer> currArr, int i) {
    if (i == arr.length) {
      if (target == 0) ans.add(new ArrayList<>(currArr));
      return;
    }

    // keep selecting curr i
    if (arr[i] <= target) {
      currArr.add(arr[i]);
      helper(arr, target - arr[i], ans, currArr, i);
      currArr.removeLast();
    }

    // reject
    helper(arr, target, ans, currArr, i + 1);
  }
}
```
