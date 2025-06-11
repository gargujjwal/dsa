---
id: lca-bst
aliases: []
tags: []
layout: default
title: LCA in BST
---

## Iterative Solution

```java
class Solution {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val > q.val) {
      return lowestCommonAncestor(root, q, p);
    }

    while (root != null) {
      if (root.val >= p.val && root.val <= q.val) {
        return root;
      }

      if (root.val > p.val && root.val > q.val) {
        root = root.left;
      }

      if (root.val < p.val && root.val < q.val) {
        root = root.right;
      }
    }

    return null;
  }
}
```
