---
id: traversal
aliases: []
tags: []
layout: default
title: All Binary Tree Traversal
---

## Pre-order Traversal

### Recursive

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }

    private void helper(TreeNode root, List<Integer> ans) {
        if (root == null) {return;}
        ans.add(root.val);
        helper(root.left, ans);
        helper(root.right, ans);
    }
}
```

### Iterative

```java
class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> st = new ArrayDeque<>();

    if (root == null) return ans;

    st.push(root);
    while (!st.isEmpty()) {
      TreeNode node = st.pop();
      ans.add(node.val);

      if (node.right != null) {
        st.push(node.right);
      }
      if (node.left != null) {
        st.push(node.left);
      }
    }

    return ans;
  }
}
```

## Inorder Traversal

### Recursive

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }

    private void helper(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        helper(root.left, ans);
        ans.add(root.val);
        helper(root.right, ans);
    }
}
```

### Iterative

```java
class Solution {
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> st = new ArrayDeque<>();

    TreeNode node = root;
    while (true) {
      if (node != null) {
        st.push(node);
        node = node.left;
      } else {
        // no more nodes left to process
        if (st.isEmpty()) {
          break;
        }
        // no more left subtree left, to process
        node = st.pop();
        ans.add(node.val);
        node = node.right;
      }
    }

    return ans;
  }
}
```

## Post-order Traversal

### Recursive

```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }

    private void helper(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        helper(root.left, ans);
        helper(root.right, ans);
        ans.add(root.val);
    }
}
```

### Iterative

```java
class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> st = new ArrayDeque<>();

    if (root == null) return ans;

    st.push(root);
    while (!st.isEmpty()) {
      root = st.pop();
      if (root.left != null) st.push(root.left);
      if (root.right != null) st.push(root.right);

      ans.add(root.val);
    }

    return ans.reversed();
  }
}
```

## Level-order Traversal

```java
import java.util.*;

class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
    Deque<Map.Entry<TreeNode, Integer>> q = new ArrayDeque<>();
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    q.offer(new AbstractMap.SimpleEntry<>(root, 1));
    while (!q.isEmpty()) {
      var p = q.poll();
      var node = p.getKey();
      var lvl = p.getValue();
      if (ans.size() != lvl) {
        ans.add(new ArrayList<>());
      }

      ans.getLast().add(node.val);

      if (node.left != null) q.offer(new AbstractMap.SimpleEntry<>(node.left, lvl + 1));
      if (node.right != null) q.offer(new AbstractMap.SimpleEntry<>(node.right, lvl + 1));
    }

    return ans;
  }
}

```
