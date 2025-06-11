---
id: construct-bst-from-preorder
aliases: []
tags: []
layout: default
title: Construct BST from Preorder
---

## Brute Solution, T:O(n ^ 2)

```java
class Solution {
  public TreeNode bstFromPreorder(int[] preorder) {
    return buildTree(preorder, 0, preorder.length - 1);
  }

  private TreeNode buildTree(int[] preorder, int start, int end) {
    if (start > end) return null;

    TreeNode root = new TreeNode(preorder[start]);
    // figure out how many elements towards its right
    int numsLeft = 0;
    for (int i = start + 1; i <= end; i++)
      if (preorder[i] < preorder[start]) numsLeft++;
      else break;

    root.left = buildTree(preorder, start + 1, start + numsLeft);
    root.right = buildTree(preorder, start + numsLeft + 1, end);

    return root;
  }
}
```

## Optimal Solution, T:O(n)

```java
class Solution {
  private int idx;

  public TreeNode bstFromPreorder(int[] preorder) {
    idx = 0;
    return buildTree(preorder, Integer.MAX_VALUE);
  }

  private TreeNode buildTree(int[] preorder, int bound) {
    if (idx >= preorder.length || preorder[idx] > bound) return null;
    TreeNode root = new TreeNode(preorder[idx]);
    idx++;
    root.left = buildTree(preorder, root.val);
    root.right = buildTree(preorder, bound);
    return root;
  }
}
```
