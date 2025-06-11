---
id: binary-tree-zigzag-level-order-traversal
aliases: []
tags: []
layout: default
title: Binary Tree Zigzag Level Order Traversal
---

## Brute Solution

```java
record Pair(TreeNode node, int lvl) {}

class Solution {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    Deque<Pair> q = new ArrayDeque<>();
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) return ans;

    q.offer(new Pair(root, 1));
    while (!q.isEmpty()) {
      Pair p = q.poll();

      if (ans.size() < p.lvl()) {
        ans.add(new ArrayList<>());
      }
      ans.getLast().add(p.node().val);

      TreeNode left = p.node().left;
      TreeNode right = p.node().right;
      int nextLvl = p.lvl() + 1;
      if (left != null) q.offer(new Pair(left, nextLvl));
      if (right != null) q.offer(new Pair(right, nextLvl));
    }

    // reverse alternate positions
    boolean shouldReverse = false;
    for (int i = 0; i < ans.size(); i++) {
      if (shouldReverse) {
        reverse(ans.get(i));
      }
      shouldReverse = !shouldReverse;
    }

    return ans;
  }

  private void reverse(List<Integer> list) {
    int left = 0;
    int right = list.size() - 1;
    while (left < right) {
      int temp = list.get(left);
      list.set(left, list.get(right));
      list.set(right, temp);
      left++;
      right--;
    }
  }
}
```

## Optimal Solution

```java
class Solution {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    Deque<TreeNode> q = new ArrayDeque<>();
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) return ans;

    boolean leftToRight = true;
    q.offer(root);
    while (!q.isEmpty()) {
      int n = q.size();
      List<Integer> row = new ArrayList<>(Collections.nCopies(n, null));

      for (int i = leftToRight ? 0 : q.size() - 1; leftToRight ? i < n : i >= 0; ) {
        TreeNode node = q.pop();
        row.set(i, node.val);

        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);

        if (leftToRight) i++;
        else i--;
      }

      leftToRight = !leftToRight;
      ans.add(row);
    }

    return ans;
  }
}
```
