---
id: diameter-of-binary-tree
aliases: []
tags: []
layout: default
title: Diameter of Binary Trees
---

## Optimal Solution

```java
record Pair(int ans, int height) {}

class Solution {
  public int diameterOfBinaryTree(TreeNode root) {
    return helper(root).ans();
  }

  private Pair helper(TreeNode root) {
    if (root == null) {
      return new Pair(0, 0);
    }

    Pair left = helper(root.left);
    Pair right = helper(root.right);

    int ans = Math.max(Math.max(left.height() + right.height(), left.ans()), right.ans());
    Pair res = new Pair(ans, Math.max(left.height(), right.height()) + 1);
    return res;
  }
}
```
