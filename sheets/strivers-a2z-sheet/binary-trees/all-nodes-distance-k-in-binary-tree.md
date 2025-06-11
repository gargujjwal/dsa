---
id: all-nodes-distance-k-in-binary-tree
aliases: []
tags: []
layout: default
title: All Nodes Distance k in Binary Tree
---

## Solution

```java

class Solution {

  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, TreeNode> parentLinks = createParentLinks(root);
    Set<Integer> visited = new HashSet<>();
    Deque<TreeNode> q = new ArrayDeque<>();
    q.offer(target);

    for (int i = 0; i < k && !q.isEmpty(); i++) {
      // move radially outward
      // first go up
      int n = q.size();

      // exhaust current lvl
      for (int j = 0; j < n; j++) {
        TreeNode tn = q.poll();
        visited.add(tn.val);
        TreeNode parent = parentLinks.get(tn);
        if (parent != null && !visited.contains(parent.val)) {
          visited.add(parent.val);
          q.offer(parent);
        }

        // go down
        if (tn.left != null && !visited.contains(tn.left.val)) {
          visited.add(tn.left.val);
          q.offer(tn.left);
        }
        if (tn.right != null && !visited.contains(tn.right.val)) {
          visited.add(tn.right.val);
          q.offer(tn.right);
        }
      }
    }

    return q.stream().mapToInt(n -> n.val).boxed().toList();
  }

  private static Map<TreeNode, TreeNode> createParentLinks(TreeNode root) {
    Map<TreeNode, TreeNode> parents = new HashMap<>();
    Deque<TreeNode> q = new ArrayDeque<>();
    q.offer(root);

    while (!q.isEmpty()) {
      TreeNode tn = q.poll();

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
