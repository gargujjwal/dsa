---
id: height-of-complete-tree
aliases: []
tags: []
layout: default
title: Height of Complete Tree
---

## Solution

```java
class Solution {
  public int countNodes(TreeNode root) {
    if (root == null) return 0;
    int leftHeight = heightOfLeftSubTree(root.left);
    int rightHeight = heightOfRightSubTree(root.right);

    if (leftHeight == rightHeight) {
      return (int) Math.pow(2, leftHeight) - 1;
    } else return countNodes(root.left) + countNodes(root.right) + 1;
  }

  private int heightOfLeftSubTree(TreeNode root) {
    if (root == null) return 0;
    return heightOfLeftSubTree(root.left) + 1;
  }

  private int heightOfRightSubTree(TreeNode root) {
    if (root == null) return 0;
    return heightOfRightSubTree(root.right) + 1;
  }
}
```
