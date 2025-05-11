---
id: longest-common-prefix
aliases: []
tags: []
layout: default
title: Longest Common Prefix
---

## Better Solution

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        // testcases whose answer can be given right away
        if (strs.length == 1) {
            return strs[0];
        }

        int i = 0;
        while (true) {
            // don't go beyond length of first string
            if (strs[0].length() - 1 < i) {
                break;
            }

            boolean isCommon = true;
            char c = strs[0].charAt(i);
            for (String s : strs) {
                if (s.length() - 1 < i || s.charAt(i) != c) {
                    isCommon = false;
                    break;
                }
            }

            if (isCommon) {
                i++;
            } else {
                break;
            }
        }

        return sb.toString();
    }
}
```

## Optimal Solution, #1

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int idx = 0;
        while(idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx) == s2.charAt(idx)){
                idx++;
            } else {
                break;
            }
        }
        return s1.substring(0, idx);
    }
}
```

# Optimal Solution #2

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        // testcases whose answer can be given right away
        if (strs.length == 1) {
            return strs[0];
        }

        int i = 0;
        while (true) {
            // don't go beyond length of first string
            if (strs[0].length() - 1 < i) {
                break;
            }

            boolean isCommon = true;
            char c = strs[0].charAt(i);
            for (String s : strs) {
                if (s.length() - 1 < i || s.charAt(i) != c) {
                    isCommon = false;
                    break;
                }
            }

            if (isCommon) {
                i++;
            } else {
                break;
            }
        }

        if (strs[0].length() == 0) {
            return "";
        }

        return strs[0].substring(0, i);
    }
}
```
