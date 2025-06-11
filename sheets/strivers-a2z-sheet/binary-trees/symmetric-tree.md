---
id: symmetric-tree
aliases: []
tags: []
layout: default
title: Symmetric Tree
---

## Solution

```java
class Solution {
  public boolean isSymmetric(TreeNode root) {
    return helper(root.left, root.right);
  }

  private static boolean helper(TreeNode first, TreeNode sec) {
    if ((first == null && sec != null) || (first != null && sec == null))
      return false;
    else if (first == null && sec == null) return true;

    if (first.val != sec.val) return false;
    if (!helper(first.left, sec.right)) {
      return false;
    }
    if (!helper(first.right, sec.left)) {
      return false;
    }

    return true;
  }
}
```
