---
id: burning-tree
aliases: []
tags: []
layout: default
title: Burning Tree
---

## Solution

```java
class Solution {

  public static int minTime(Node root, int target) {
    // find node
    Node tar = findNode(root, target);

    // get parent links
    Map<Node, Node> parentLinks = createParentLinks(root);
    Set<Integer> visited = new HashSet<>();
    Deque<Node> q = new ArrayDeque<>();
    q.offer(tar);
    visited.add(tar.data);

    int ans = 0;
    for (; !q.isEmpty(); ans++) {
      // move radially outward
      // first go up
      int n = q.size();

      // exhaust current lvl
      for (int j = 0; j < n; j++) {
        Node tn = q.poll();
        Node parent = parentLinks.get(tn);
        if (parent != null && !visited.contains(parent.data)) {
          visited.add(parent.data);
          q.offer(parent);
        }

        // go down
        if (tn.left != null && !visited.contains(tn.left.data)) {
          visited.add(tn.left.data);
          q.offer(tn.left);
        }
        if (tn.right != null && !visited.contains(tn.right.data)) {
          visited.add(tn.right.data);
          q.offer(tn.right);
        }
      }
    }

    return ans - 1;
  }

  public static Node findNode(Node root, int target) {
    if (root == null) {
      return null;
    }
    if (root.data == target) {
      return root;
    }

    Node leftSubTreeSearch = findNode(root.left, target);
    if (leftSubTreeSearch != null) {
      return leftSubTreeSearch;
    }
    Node rightSubTreeSearch = findNode(root.right, target);
    return rightSubTreeSearch;
  }

  private static Map<Node, Node> createParentLinks(Node root) {
    Map<Node, Node> parents = new HashMap<>();
    Deque<Node> q = new ArrayDeque<>();
    q.offer(root);

    while (!q.isEmpty()) {
      Node tn = q.poll();

      if (tn.left != null) {
        parents.put(tn.left, tn);
        q.offer(tn.left);
      }
      if (tn.right != null) {
        parents.put(tn.right, tn);
        q.offer(tn.right);
      }
    }

    return parents;
  }
}
```
