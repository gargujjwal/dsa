---
id: word-break
aliases: []
tags: []
layout: default
title: Word Break
---

## Brute Solution

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int minLen = wordDict.stream().reduce((s1, s2) -> s1.length() < s2.length() ? s1 : s2).get().length();
        return helper(0, minLen, s, new HashSet<>(wordDict));
    }

    private boolean helper(int startIdx, int minIncrement, String s, Set<String> wordDict) {
        if (startIdx == s.length()) {
            return true;
        }

        for (int i = startIdx + minIncrement; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(startIdx, i))) {
                if (helper(i, minIncrement, s, wordDict)) {
                    return true;
                }
            }
        }

        return false;
    }
}
```

## Optimal Solution

```java
public class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
    int minLen =
        wordDict.stream().reduce((s1, s2) -> s1.length() < s2.length() ? s1 : s2).get().length();
    int maxLen =
        wordDict.stream().reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2).get().length();

    return helper(0, minLen, maxLen, s, new HashSet<>(wordDict), new HashMap<>());
  }

  private boolean helper(
      int startIdx,
      int minIncrement,
      int maxIncrement,
      String s,
      Set<String> wordDict,
      Map<Integer, Boolean> memo) {
    if (startIdx == s.length()) {
      return true;
    }

    if (memo.containsKey(startIdx)) {
      return memo.get(startIdx);
    }

    for (int i = startIdx + minIncrement; i <= Math.min(startIdx + maxIncrement, s.length()); i++) {
      if (wordDict.contains(s.substring(startIdx, i))) {
        if (helper(i, minIncrement, maxIncrement, s, wordDict, memo)) {
          memo.put(startIdx, true);
          return true;
        }
      }
    }

    memo.put(startIdx, false);
    return false;
  }
}
```
