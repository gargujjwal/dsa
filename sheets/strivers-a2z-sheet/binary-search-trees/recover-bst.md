---
id: recover-bst
aliases: []
tags: []
layout: default
title: Recover BST
---

## Brute Solution

```java
class Solution {
  public void recoverTree(TreeNode root) {
    List<Integer> inorder = new ArrayList<>();
    inorderTraversal(root, inorder);
    sort(inorder);
    buildTree(root, inorder, new int[] {0});
  }

  private void buildTree(TreeNode root, List<Integer> inorder, int[] idx) {
    if (root == null) return;
    buildTree(root.left, inorder, idx);
    root.val = inorder.get(idx[0]);
    idx[0]++;
    buildTree(root.right, inorder, idx);
  }

  private static void sort(List<Integer> arr) {
    int left = 0;
    while (left != arr.size() - 1 && arr.get(left) < arr.get(left + 1)) {
      left++;
    }

    int right = arr.size() - 1;
    while (right >= 0 && arr.get(right) > arr.get(right - 1)) {
      right--;
    }

    int temp = arr.get(left);
    arr.set(left, arr.get(right));
    arr.set(right, temp);
  }

  private static void inorderTraversal(TreeNode root, List<Integer> ans) {
    if (root == null) return;
    inorderTraversal(root.left, ans);
    ans.add(root.val);
    inorderTraversal(root.right, ans);
  }
}
```

## Optimal Solution

```java
class Solution {
  private TreeNode prev;
  private TreeNode first;
  private TreeNode middle;

  public void recoverTree(TreeNode root) {
    prev = first = middle = null;
    inorderTraversal(root);
    if (first != null) {
      swap(first, middle);
    }
  }

  private void inorderTraversal(TreeNode root) {
    if (root == null) return;
    inorderTraversal(root.left);
    if (prev != null) {
      // see if in violation
      if (prev.val > root.val) {
        if (first == null) {
          // first violation
          first = prev;
          middle = root;
        } else {
          // second violation meaning swap first and second
          swap(first, root);
          first = null;
          middle = null;
        }
      }
    }
    prev = root;
    inorderTraversal(root.right);
  }

  private void swap(TreeNode first, TreeNode sec) {
    int temp = first.val;
    first.val = sec.val;
    sec.val = temp;
  }
}

```
