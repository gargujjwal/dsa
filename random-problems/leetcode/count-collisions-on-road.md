---
id: count-collisions-on-road
aliases: []
tags: []
layout: default
title: Count Collisions on Road
---

## Brute Solution

```java
class Solution {
  private final char R = 'R';
  private final char L = 'L';
  private final char S = 'S';

  public int countCollisions(String directions) {
    Deque<Character> s = new ArrayDeque<>();
    int ans = 0;

    for (int i = 0; i < directions.length(); i++) {
      char c = directions.charAt(i);
      if (s.isEmpty()) {
        s.push(c);
        continue;
      }

      char top = s.pop();
      // conditions for collisions are
      // 1. Opposite directions R,L
      // 2. with Stationary care R,S ; S,L

      if (top == R && c == L) {
        ans += 2;
        s.push(S);
      } else if ((top == R && c == S) || (top == S && c == L)) {
        ans++;
        s.push(S);
      } else {
        s.push(top);
        s.push(c);
      }
    }

    // calculate residual collisions
    while (s.size() > 1) {
      char first = s.pop();
      char sec = s.pop();

      if (sec == R && first == L) {
        ans += 2;
        s.push(S);
      } else if ((sec == R && first == S) || (sec == S && first == L)) {
        ans++;
        s.push(S);
      } else {
        s.push(sec);
      }
    }

    return ans;
  }
}
```
