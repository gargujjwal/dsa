---
id: kth-smallest
aliases: []
tags: []
title: Kth Smallest
---

## Solution

```java
class Solution {
  private int ans;
  private int cnt;

  public int kthSmallest(TreeNode root, int k) {
    ans = -1;
    cnt = 0;

    helper(root, k);

    return ans;
  }

  private void helper(TreeNode root, int k) {
    if (root == null || cnt > k) return;

    helper(root.left, k);
    cnt++;
    if (cnt == k) {
      ans = root.val;
      return;
    }
    helper(root.right, k);
  }
}
```
