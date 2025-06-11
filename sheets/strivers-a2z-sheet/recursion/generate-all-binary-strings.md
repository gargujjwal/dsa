---
id: generate-all-binary-strings
aliases: []
tags: []
layout: default
title: Generate All Binary Strings
---

## Optimal Solution

```java
class Solution {
    public static List<String> generateBinaryStrings(int n) {
        List<String> ans = new ArrayList<>();
        helper(ans, new StringBuilder(), n);
        return ans;
    }

    private static void helper(List<String> answer, StringBuilder currStr, int n) {
        if (n == 0) {
            answer.add(currStr.toString());
            return;
        }
        currStr.append('0');
        helper(answer, currStr, n - 1);
        currStr.deleteCharAt(currStr.length() - 1);
        if (currStr.length() == 0 || currStr.charAt(currStr.length() - 1) == '0') {
            currStr.append('1');
            helper(answer, currStr, n - 1);
            currStr.deleteCharAt(currStr.length() - 1);

        }
    }
}
```
