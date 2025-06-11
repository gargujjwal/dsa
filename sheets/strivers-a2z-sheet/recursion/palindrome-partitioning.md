---
id: palindrome-partitioning
aliases: []
tags: []
layout: default
title: Palindrome Partitioning
---

## Optimal Solution

```java
class Solution {
  public List<List<String>> partition(String s) {
    List<List<String>> ans = new ArrayList<>();
    helper(0, new ArrayList<>(), ans, s);
    return ans;
  }

  private void helper(int idx, List<String> currAns, List<List<String>> ans, String s) {
    if (idx == s.length()) {
      ans.add(new ArrayList<>(currAns));
      return;
    }

    for (int i = idx; i < s.length(); i++) {
      if (isPalindromic(s, idx, i)) {
        currAns.add(s.substring(idx, i + 1));
        helper(i + 1, currAns, ans, s);
        currAns.removeLast();
      }
    }
  }

  public boolean isPalindromic(String s, int start, int end) {
    while (start < end) {
      char st = s.charAt(start);
      char en = s.charAt(end);
      if (st != en) {
        return false;
      }
      start++;
      end--;
    }

    return true;
  }
}
```
