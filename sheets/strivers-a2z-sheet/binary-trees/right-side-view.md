---
id: right-side-view
aliases: []
tags: []
layout: default
title: Right Side View
---

## Optimal Solution

```java
class Solution {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    helper(root, 0, new HashSet<>(), ans);
    return ans;
  }

  private void helper(TreeNode root, int lvl, Set<Integer> seenLvl, List<Integer> ans) {
    if (root == null) return;
    if (!seenLvl.contains(lvl)) {
      seenLvl.add(lvl);
      ans.add(root.val);
    }

    helper(root.right, lvl + 1, seenLvl, ans);
    helper(root.left, lvl + 1, seenLvl, ans);
  }
}
```
