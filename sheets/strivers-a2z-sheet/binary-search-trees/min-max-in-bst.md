---
id: min-max-in-bst
aliases: []
tags: []
layout: default
title: Min Max in BST
---

## Solution

```java
class Tree {
  int min(Node root) {
    while (root.left != null) root = root.left;
    return root.data;
  }
}
```

```java
class Tree {
  int max(Node root) {
    while (root.right != null) root = root.right;
    return root.data;
  }
}
```
