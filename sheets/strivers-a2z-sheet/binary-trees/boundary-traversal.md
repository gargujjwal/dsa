---
id: boundary-traversal
aliases: []
tags: []
layout: default
title: Boundary Traversal
---

## Solution

```java
class Solution {
  ArrayList<Integer> boundaryTraversal(Node root) {
    var ans = new ArrayList<Integer>();

    if (!isLeaf(root)) ans.add(root.data);
    leftBoundaryTraversal(root.left, ans);
    leafBoundaryTraversal(root, ans);
    rightBoundaryTraversal(root.right, ans);

    return ans;
  }

  private static void leftBoundaryTraversal(Node root, List<Integer> traversal) {
    while (root != null) {
      if (!isLeaf(root)) traversal.add(root.data);
      if (root.left != null) root = root.left;
      else root = root.right;
    }
  }

  private static void leafBoundaryTraversal(Node root, List<Integer> traversal) {
    if (root == null) return;
    if (isLeaf(root)) {
      traversal.add(root.data);
      return;
    }

    leafBoundaryTraversal(root.left, traversal);
    leafBoundaryTraversal(root.right, traversal);
  }

  private static void rightBoundaryTraversal(Node root, List<Integer> traversal) {
    var temp = new ArrayList<Integer>();
    while (root != null) {
      if (!isLeaf(root)) temp.add(root.data);
      if (root.right != null) root = root.right;
      else root = root.left;
    }

    // Reverse and add to result
    for (int i = temp.size() - 1; i >= 0; i--) {
      traversal.add(temp.get(i));
    }
  }

  private static boolean isLeaf(Node root) {
    return root.left == null && root.right == null;
  }
}
```
