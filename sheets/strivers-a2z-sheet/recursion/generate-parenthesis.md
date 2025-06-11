---
id: generate-parenthesis
aliases: []
tags: []
layout: default
title: Generate Parenthesis
---

## Solution

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper(ans, new StringBuilder(), n, n);

        return ans;
    }

    private void helper(List<String> ans, StringBuilder currStr, int nOpenBr, int nClosedBr) {
        if (nClosedBr == 0 && nOpenBr == 0) {
            ans.add(currStr.toString());
            return;
        }

        if (nOpenBr > 0) {
            currStr.append('(');
            helper(ans, currStr, nOpenBr - 1, nClosedBr);
            currStr.deleteCharAt(currStr.length() - 1);
        }

        if (nClosedBr > nOpenBr) {
            currStr.append(')');
            helper(ans, currStr, nOpenBr, nClosedBr - 1);
            currStr.deleteCharAt(currStr.length() - 1);
        }
    }
}
```
