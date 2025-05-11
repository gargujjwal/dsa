---
id: maximum-nesting-of-parenthesis
aliases: []
tags: []
layout: default
title: Maximum Nesting of Parenthesis
---

## Solution

```java
class Solution {
    public int maxDepth(String s) {
        int ans = 0;

        int lvl = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lvl++;
                ans = Math.max(ans, lvl);
            } else if (c == ')') {
                lvl--;
            }
        }
        return ans;
    }
}
```
