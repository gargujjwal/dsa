---
id: longest-palindromic-substring
aliases: []
tags: []
layout: default
title: Longest Palindromic Substring
---

## Brute Solution

```java
class Solution {
    public String longestPalindrome(String s) {
        int startIdx = 0;
        int endIdx = 0;
        int longestLength = 1;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                int length = j - i + 1;
                if (isPalindromic(s, i, j) && length > longestLength) {
                    longestLength = length;
                    startIdx = i;
                    endIdx = j;
                }
            }
        }
        return s.substring(startIdx, endIdx + 1);
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

## Optimal Solution

```java
class Solution {
    public String longestPalindrome(String s) {
        int startIdx = 0;
        int endIdx = 0;
        int maxLen = 0;
        int n = s.length();
        for (int start = 0; start < n; start++) {
            // odd length palindrome
            int left = start;
            int right = start;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int currLen = right - left + 1;
                if (currLen > maxLen) {
                    startIdx = left;
                    endIdx = right;
                    maxLen = currLen;
                }
                left--;
                right++;
            }

            // even length palindrome
            left = start;
            right = start + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int currLen = right - left + 1;
                if (currLen > maxLen) {
                    startIdx = left;
                    endIdx = right;
                    maxLen = currLen;
                }
                left--;
                right++;
            }
        }

        return s.substring(startIdx, endIdx + 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("babad"));
    }
}
```
