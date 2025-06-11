---
id: remove-k-digits
aliases: []
tags: []
layout: default
title: Remove K Digits
---

## Solution

```java
class Solution {
  public String removeKdigits(String num, int k) {
    Deque<Character> st = new ArrayDeque<>();

    for (int i = 0; i < num.length(); i++) {
      char c = num.charAt(i);
      while (!st.isEmpty() && c < st.peek() && k != 0) {
        st.pop();
        k--;
      }
      st.push(c);
    }

    while (k != 0) {
      st.pop();
      k--;
    }
    if (st.isEmpty()) return "0";

    // we have result, make the string now
    StringBuilder sb = new StringBuilder(st.size());
    while (!st.isEmpty()) {
      sb.append(st.pop());
    }
    sb.reverse();
    // remove leading zeroes
    while (!sb.isEmpty() && sb.charAt(0) == '0') {
      sb.deleteCharAt(0);
    }

    return sb.isEmpty() ? "0" : sb.toString();
  }
}
```
