---
id: ciel-in-bst
aliases: []
tags: []
layout: default
title: Ceil in BST
---

## Solution

```java
class Tree {
  int findCeil(Node root, int key) {
    Node node = root;
    int ans = -1;

    while (node != null) {
      if (node.data == key) {
        ans = key;
        break;
      } else if (node.data > key) {
        ans = node.data;
        node = node.left;
      } else {
        node = node.right;
      }
    }

    return ans;
  }
}

```
