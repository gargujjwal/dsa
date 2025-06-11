---
id: largest-bst
aliases: []
tags: []
layout: default
title: Largest BST
---

## Brute Solution

```java
class Solution {

  public static int largestBst(Node root) {
    int ans = 1;
    Deque<Node> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
      Node node = q.pop();
      if (isValidBst(node, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
        ans = Math.max(ans, numberOfNodes(node));
      } else {
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
      }
    }

    return ans;
  }

  private static int numberOfNodes(Node root) {
    if (root == null) return 0;
    return 1 + numberOfNodes(root.left) + numberOfNodes(root.right);
  }

  private static boolean isValidBst(Node root, int low, int high) {
    if (root == null) return true;
    if (root.data <= low || root.data >= high) return false;
    return isValidBst(root.left, low, root.data) && isValidBst(root.right, root.data, high);
  }
}
```

### Optimized Brute

```java
class Solution {

  public static int largestBst(Node root) {
    int ans = 1;
    Deque<Node> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
      Node node = q.pop();
      int[] numOfNodes = new int[] {0};
      if (isValidBst(node, Integer.MIN_VALUE, Integer.MAX_VALUE, numOfNodes)) {
        ans = Math.max(ans, numOfNodes[0]);
      } else {
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
      }
    }

    return ans;
  }

  private static boolean isValidBst(Node root, int low, int high, int[] numOfNodes) {
    if (root == null) return true;
    if (root.data <= low || root.data >= high) {
      numOfNodes[0] = -1;
      return false;
    }
    if (numOfNodes[0] != -1) numOfNodes[0]++;
    return isValidBst(root.left, low, root.data, numOfNodes)
        && isValidBst(root.right, root.data, high, numOfNodes);
  }
}
```

## Optimal

```java
class Info {
  int min;
  int max;
  int size;

  public Info(int min, int max, int size) {
    this.min = min;
    this.max = max;
    this.size = size;
  }
}

class Solution {

  public static int largestBst(Node root) {
    return helper(root).size;
  }

  private static Info helper(Node root) {
    if (root == null) return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    Info left = helper(root.left);
    Info right = helper(root.right);

    if (root.data > left.max && root.data < right.min) {
      // a bst
      return new Info(
          Math.min(root.data, left.min),
          Math.max(root.data, right.max),
          1 + left.size + right.size);
    }

    return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.size, right.size));
  }
}
```
