---
id: maximum-width-of-tree
aliases: []
tags: []
layout: default
title: Maximum Width of Tree
---

## Solution

```java
record Pair(TreeNode node, int idx) {}

class Solution {

  public int widthOfBinaryTree(TreeNode root) {
    Deque<Pair> q = new ArrayDeque<>();
    int ans = 0;

    q.offer(new Pair(root, 0));
    while (!q.isEmpty()) {
      int n = q.size();
      int min = q.peek().idx();

      int firstIdx = -1;
      int lastIdx = -1;

      for (int i = 0; i < n; i++) {
        Pair p = q.poll();

        int currIdx = p.idx() - min;
        if (i == 0) firstIdx = p.idx();
        else if (i == n - 1) lastIdx = p.idx();

        if (p.node().left != null) {
          q.offer(new Pair(p.node().left, 2 * currIdx + 1));
        }
        if (p.node().right != null) {
          q.offer(new Pair(p.node().right, 2 * currIdx + 2));
        }
      }

      ans = Math.max(ans, lastIdx - firstIdx + 1);
    }

    return ans;
  }
}
```
