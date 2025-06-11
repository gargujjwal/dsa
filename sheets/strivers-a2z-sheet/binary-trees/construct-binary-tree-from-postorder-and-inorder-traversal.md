---
id: construct-binary-tree-from-postorder-and-inorder-traversal
aliases: []
tags: []
layout: default
title: Construct Binary tree form Postorder and Inorder Traversal
---

## Solution

```java
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int val) {
    this.val = val;
  }
}

class Solution {
  Map<Integer, Integer> inorderCache = new HashMap<>();

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    // populate the cache
    for (int i = 0; i < inorder.length; i++) inorderCache.put(inorder[i], i);

    // Call the recursive function with full arrays and return the result
    return buildTree(0, inorder.length - 1, postorder, 0, postorder.length - 1);
  }

  private TreeNode buildTree(int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
    // Base case
    if (inStart > inEnd || postStart > postEnd) {
      return null;
    }

    // Find the root node from the last element of postorder traversal
    int rootVal = postorder[postEnd];
    TreeNode root = new TreeNode(rootVal);

    // Find the index of the root node in inorder traversal
    int rootIndex = inorderCache.get(rootVal);

    // Recursively build the left and right subtrees
    int leftSize = rootIndex - inStart;
    int rightSize = inEnd - rootIndex;
    root.left = buildTree(inStart, rootIndex - 1, postorder, postStart, postStart + leftSize - 1);
    root.right = buildTree(rootIndex + 1, inEnd, postorder, postEnd - rightSize, postEnd - 1);

    return root;
  }
}
```
