---
id: binary-search-tree-iterator
aliases: []
tags: []
layout: default
title: Binary Search Tree Iterator
---

## Solution

```java
class BSTIterator {
  private TreeNode n;
  private Deque<TreeNode> st = new ArrayDeque<>();

  public BSTIterator(TreeNode root) {
    this.n = root;
  }

  public int next() {
    while (n != null) {
      st.push(n);
      n = n.left;
    }
    TreeNode ans = st.pop();
    n = ans.right;
    return ans.val;
  }

  public boolean hasNext() {
    return !st.isEmpty() || this.n != null;
  }
}
```
