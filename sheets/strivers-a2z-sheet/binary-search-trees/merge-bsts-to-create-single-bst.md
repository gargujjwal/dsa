---
id: merge-bsts-to-create-single-bst
aliases: []
tags: []
layout: default
title: Merge BSTS to create single BST
---

## Brute Solution

```java
class Solution {
  public TreeNode canMerge(List<TreeNode> trees) {
    if (trees.size() == 1) return trees.getFirst();

    for (int i = 0; i < trees.size(); i++) {
      TreeNode iRoot = trees.get(i);
      List<TreeNode> leaves = getLeavesOfTrees(trees.get(i));
      for (TreeNode leaf : leaves) {
        for (int k = 0; k < trees.size(); k++) {
          if (k == i) continue;

          TreeNode jRoot = trees.get(k);
          if (jRoot.val != leaf.val) continue;

          // merge both trees
          leaf.left = jRoot.left;
          leaf.right = jRoot.right;
          trees.remove(k);

          if (isValidBst(iRoot)) {
            return canMerge(trees);
          } else {
            return null;
          }
        }
      }
    }

    return null;
  }

  private boolean isValidBst(TreeNode root) {
    return isValidBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isValidBst(TreeNode root, int start, int end) {
    if (root == null) return true;
    if (!(root.val > start && root.val < end)) {
      return false;
    }

    return isValidBst(root.left, start, root.val) && isValidBst(root.right, root.val, end);
  }

  private List<TreeNode> getLeavesOfTrees(TreeNode root) {
    List<TreeNode> ans = new ArrayList<>();
    helper(root, ans);
    return ans;
  }

  private void helper(TreeNode root, List<TreeNode> ans) {
    if (root == null) return;
    if (isLeaf(root)) {
      ans.add(root);
      return;
    }
    helper(root.left, ans);
    helper(root.right, ans);
  }

  private boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }
}
```
