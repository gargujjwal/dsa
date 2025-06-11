---
id: lowest-common-ancestor
aliases: []
tags: []
layout: default
title: LCA tree
---

## Brute Solution T:O(n^2)

```java
class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode ans = null;
    Deque<TreeNode> qu = new ArrayDeque<>();
    qu.offer(root);
    while (!qu.isEmpty()) {
      TreeNode node = qu.poll();
      if (isCommonAncestor(node, p, q)) {
        ans = node;
      }

      if (node.left != null) {
        qu.offer(node.left);
      }
      if (node.right != null) {
        qu.offer(node.right);
      }
    }

    return ans;
  }

  private boolean isCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
    return canReachNode(node, p) && canReachNode(node, q);
  }

  private boolean canReachNode(TreeNode node, TreeNode p) {
    if (node == null) {
      return false;
    }
    if (node.val == p.val) {
      return true;
    }

    if (canReachNode(node.left, p)) {
      return true;
    }
    if (canReachNode(node.right, p)) {
      return true;
    }

    return false;
  }
}
```

## Optimal Solution, T:O(n)

```java
class Solution {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    if (root.val == p.val) return root;
    else if (root.val == q.val) return root;

    TreeNode leftSubTreeSearch = lowestCommonAncestor(root.left, p, q);
    TreeNode rightSubTreeSearch = lowestCommonAncestor(root.right, p, q);

    if (leftSubTreeSearch == null && rightSubTreeSearch == null) return null;
    else if (leftSubTreeSearch == null && rightSubTreeSearch != null) return rightSubTreeSearch;
    else if (leftSubTreeSearch != null && rightSubTreeSearch == null) return leftSubTreeSearch;
    else {
      return root;
    }
  }
}
```

```java
class Solution {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root.val == p.val || root.val == q.val) return root;

    TreeNode leftSubTreeSearch = lowestCommonAncestor(root.left, p, q);
    TreeNode rightSubTreeSearch = lowestCommonAncestor(root.right, p, q);

    if (leftSubTreeSearch == null) return rightSubTreeSearch;
    else if (rightSubTreeSearch == null) return leftSubTreeSearch;
    else return root;
  }
}
```
