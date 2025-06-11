---
id: same-tree
aliases: []
tags: []
layout: default
title: Same Tree
---

## Solution

```java
class Solution {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    // if any of two nodes are not equal then its not the same tree
    if ((p == null && q != null) || (p != null && q == null)) return false;

    // if both nodes same then might be same tree
    if (p == null && q == null) return true;

    if (p.val == q.val) {
      // check both subtree
      return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    return false;
  }
}
```
