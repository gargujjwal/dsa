---
id: delete-node-from-bst
aliases: []
tags: []
layout: default
title: Delete Node from BST
---

## Iterative Solution

```java
class Solution {
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;
    if (root.left == null && root.right == null && root.val == key) {
      return null;
    }

    TreeNode n = root;
    TreeNode prev = n;
    while (n != null) {
      if (n.val == key) {
        break;
      } else if (n.val > key) {
        prev = n;
        n = n.left;
      } else {
        prev = n;
        n = n.right;
      }
    }
    // counldn't find the node
    if (n == null) return root;
    // node to be deleted is leaf node
    if (n.right == null && n.left == null) {
      if (prev.left == n) prev.left = null;
      else prev.right = null;
      return root;
    }

    if (n.right == null && n.left != null) {
      n.val = n.left.val;
      n.right = n.left.right;
      n.left = n.left.left;
    } else if (n.right != null && n.left == null) {
      n.val = n.right.val;
      n.left = n.right.left;
      n.right = n.right.right;
    } else {
      // going to attach the left subtree to the smallest node of right
      // subtree
      TreeNode oldLeft = n.left;
      n.val = n.right.val;
      n.left = n.right.left;
      n.right = n.right.right;

      // now continue to go down right subtree
      while (n.left != null) {
        n = n.left;
      }
      n.left = oldLeft;
    }

    return root;
  }
}
```

## Recursive Solution

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node to delete found
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Two children: find in-order successor (smallest in right subtree)
            TreeNode successor = getMin(root.right);
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }

    private TreeNode getMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
```
