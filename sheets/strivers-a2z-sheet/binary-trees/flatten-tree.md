---
id: flatten-tree
aliases: []
tags: []
layout: default
title: Flatten Tree
---

## Brute Solution, O(n ^ 2)

```java
class Solution {

  private void flatten(TreeNode root) {
    if (root == null) return;
    if (root.left != null) {
      // attach this branch to its right side
      TreeNode oldRight = root.right;
      root.right = root.left;
      root.left = null;

      TreeNode lastRight = root;
      while (lastRight.right != null) {
        lastRight = lastRight.right;
      }
      lastRight.right = oldRight;
    }

    flatten(root.right);
  }
}
```

## Optimal Solution

```java
class Solution {
  private TreeNode prev = null;

  public void flatten(TreeNode root) {
    if (root == null) return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
  }
}
```
