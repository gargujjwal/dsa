---
id: floor-in-bst
aliases: []
tags: []
layout: default
title: Floor in BST
---

## Solution

```java
class Solution {
  public static int floor(Node root, int x) {
    int ans = -1;
    while (root != null) {
      if (root.data < x) {
        ans = root.data;
        root = root.right;
      } else if (root.data == x) {
        return x;
      } else {
        root = root.left;
      }
    }

    return ans;
  }
}

```
