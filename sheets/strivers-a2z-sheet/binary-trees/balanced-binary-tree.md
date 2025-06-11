---
id: balanced-binary-tree
aliases: []
tags: []
layout: default
title: Balanced Binary Tree
---

## Optimal Solution

```java
class Solution {
  public boolean isBalanced(TreeNode root) {
    if (root == null) return true;
    return helper(root) != -1;
  }

  private int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }

    // check imbalance in left tree
    int leftTree = helper(root.left);
    if (leftTree == -1) return -1;
    // check imbalance in right tree
    int rightTree = helper(root.right);
    if (rightTree == -1) return -1;

    // imbalance detected
    if (Math.abs(leftTree - rightTree) > 1) return -1;

    return Math.max(leftTree, rightTree) + 1;
  }
}
```
