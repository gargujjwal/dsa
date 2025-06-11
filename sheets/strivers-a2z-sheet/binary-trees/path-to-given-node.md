---
id: path-to-given-node
aliases: []
tags: []
layout: default
title: Path to Given Node
---

## Solution

```java
public class Solution {
  public int[] solve(TreeNode A, int B) {
    List<Integer> ans = new ArrayList<>();
    helper(A, B, new ArrayList<>(), ans);
    return ans.stream().mapToInt(e -> e).toArray();
  }

  private boolean helper(TreeNode A, int b, List<Integer> path, List<Integer> ans) {
    if (A == null) return false;
    if (b == A.val) {
      ans.addAll(path);
      ans.add(A.val);
      return true;
    }

    path.add(A.val);
    if (helper(A.left, b, path, ans)) {
      return true;
    }
    if (helper(A.right, b, path, ans)) {
      return true;
    }
    path.remove(path.size() - 1);

    return false;
  }
}
```
