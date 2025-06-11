---
id: validate-bst
aliases: []
tags: []
layout: default
title: Validate BST
---

## Optimal

```java
class Solution {
  private long max;
  private boolean isValid;

  public boolean isValidBST(TreeNode root) {
    max = Long.MIN_VALUE;
    isValid = true;
    helper(root);
    return isValid;
  }

  private void helper(TreeNode root) {
    if (root == null) return;
    helper(root.left);
    if (max >= root.val) {
      isValid = false;
      return;
    }
    max = root.val;
    helper(root.right);
  }
}
```

## Optimal Solution Using Ranges

```java
class Solution {
  public boolean isValidBST(TreeNode root) {
    return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean helper(TreeNode root, long start, long end) {
    if (root == null) return true;
    return root.val > start
        && root.val < end
        && helper(root.left, start, root.val)
        && helper(root.right, root.val, end);
  }
}
```
