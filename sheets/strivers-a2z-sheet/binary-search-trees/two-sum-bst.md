---
id: two-sum-bst
aliases: []
tags: []
layout: default
title: Two Sum in BST
---

## Solution

```java
import java.util.ArrayDeque;
import java.util.Deque;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(final int val) {
    this.val = val;
  }
}

class BSTIterator {
  private final Deque<TreeNode> st = new ArrayDeque<>();
  private final boolean reverse;

  public BSTIterator(final TreeNode root, final boolean reverse) {
    this.reverse = reverse;
    pushInStack(root);
  }

  public int next() {
    final TreeNode node = st.pop();
    if (reverse) pushInStack(node.left);
    else pushInStack(node.right);
    return node.val;
  }

  public boolean hasNext() {
    return !st.isEmpty();
  }

  private void pushInStack(TreeNode root) {
    while (root != null) {
      st.push(root);
      if (reverse) {
        root = root.right;
      } else {
        root = root.left;
      }
    }
  }
}

class Solution {
  public boolean findTarget(final TreeNode root, final int k) {
    final BSTIterator left = new BSTIterator(root, false);
    final BSTIterator right = new BSTIterator(root, true);

    int i = left.next();
    int j = right.next();
    while (i < j) {
      final int sum = i + j;
      if (sum < k) {
        i = left.next();
      } else if (sum > k) {
        j = right.next();
      } else {
        return true;
      }
    }

    return false;
  }
}

```
