---
id: insert-a-given-node-in-bst
aliases: []
tags: []
layout: default
title: Insert a Given Node in BST
---

## Solution

```java
class Solution {
  public TreeNode insertIntoBST(TreeNode root, int val) {
    TreeNode nNode = new TreeNode(val);
    if (root == null) return nNode;
    TreeNode node = root;
    while (true) {
      if (node.val > val) {
        if (node.left == null) {
          break;
        } else {
          node = node.left;
        }
      } else {
        if (node.right == null) {
          break;
        } else {
          node = node.right;
        }
      }
    }

    if (node.val > val) {
      node.left = nNode;
    } else {
      node.right = nNode;
    }

    return root;
  }
}
```
