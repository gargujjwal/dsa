---
id: binary-tree-maximum-path-sum
aliases: []
tags: []
layout: default
title: Binary Tree Maximum Path Sum
---

## My Solution

```java
class Solution {
  public int maxPathSum(TreeNode root) {
    int[] ans = new int[] {Integer.MIN_VALUE};
    helper(root, ans);
    return ans[0];
  }

  private int helper(TreeNode root, int[] max) {
    if (root == null) return 0;

    int leftMaxPathSum = helper(root.left, max);
    int rightMaxPathSum = helper(root.right, max);

    max[0] =
        maximum(
            max[0],
            leftMaxPathSum + rightMaxPathSum + root.val,
            leftMaxPathSum + root.val,
            rightMaxPathSum + root.val,
            root.val);

    return Math.max(Math.max(leftMaxPathSum, rightMaxPathSum) + root.val, root.val);
  }

  private int maximum(int... args) {
    int ans = Integer.MIN_VALUE;
    for (int i : args) ans = Math.max(ans, i);
    return ans;
  }
}
```

## Optimal Solution

```java
class Solution {
  public int maxPathSum(TreeNode root) {
    int[] ans = new int[] {Integer.MIN_VALUE};
    helper(root, ans);
    return ans[0];
  }

  private int helper(TreeNode root, int[] max) {
    if (root == null) return 0;

    // don't consider -ve path sum from its child
    int leftMaxPathSum = Math.max(0, helper(root.left, max));
    int rightMaxPathSum = Math.max(0, helper(root.right, max));

    max[0] = Math.max(max[0], leftMaxPathSum + rightMaxPathSum + root.val);

    // choose the subtree with maximum sum path
    return Math.max(leftMaxPathSum, rightMaxPathSum) + root.val;
  }
}
```
