---
id: letter-combination-of-a-phone-number
aliases: []
tags: []
layout: default
title: Letter Combination of a Phone Number
---

## Optimal Solution

```java
class Solution {
  private static final Map<Character, List<Character>> PHONE =
      Map.of(
          '2', List.of('a', 'b', 'c'),
          '3', List.of('d', 'e', 'f'),
          '4', List.of('g', 'h', 'i'),
          '5', List.of('j', 'k', 'l'),
          '6', List.of('m', 'n', 'o'),
          '7', List.of('p', 'q', 'r', 's'),
          '8', List.of('t', 'u', 'v'),
          '9', List.of('w', 'x', 'y', 'z'));

  public List<String> letterCombinations(String digits) {
    List<String> ans = new ArrayList<>();
    if (digits.isEmpty()) {
      return ans;
    }

    helper(0, new StringBuilder(), ans, digits);
    return ans;
  }

  private void helper(int idx, StringBuilder currAns, List<String> ans, String digits) {
    if (currAns.length() == digits.length()) {
      ans.add(currAns.toString());
      return;
    }

    for (char c : PHONE.get(digits.charAt(idx))) {
      currAns.append(c);
      helper(idx + 1, currAns, ans, digits);
      currAns.deleteCharAt(currAns.length() - 1);
    }
  }
}
```
