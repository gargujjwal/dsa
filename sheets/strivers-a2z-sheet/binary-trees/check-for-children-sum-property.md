---
id: check-for-children-sum-property
aliases: []
tags: []
layout: default
title: Check for Children sum Property
---

## Solution

```java
class Solution {

  public static int isSumProperty(Node root) {
    if (root == null) return 1;
    if (isLeafNode(root)) return 1;

    int sum = (root.left == null ? 0 : root.left.data) + (root.right == null ? 0 : root.right.data);
    if (sum != root.data) {
      return 0;
    }

    return (isSumProperty(root.left) + isSumProperty(root.right)) / 2;
  }

  private static boolean isLeafNode(Node root) {
    return root != null && root.left == null && root.right == null;
  }
}
```
