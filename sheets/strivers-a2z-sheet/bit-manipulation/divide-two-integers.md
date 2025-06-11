---
id: divide-two-integers
aliases: []
tags: []
layout: default
title: Divide Two Integers
---

```java
class Solution {
  public int divide(int dividend, int divisor) {
    // some conditions where we know the answer
    if (dividend == divisor) return 1;
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }

    if (divisor == 1) return dividend;
    if (dividend == -1) return -dividend;
    // true -> +ve, false -> -ve
    boolean sign = (dividend >= 0 && divisor > 0) || (dividend < 0 && divisor < 0);

    // make both positive
    long n = Math.abs((long) dividend);
    long d = Math.abs((long) divisor);

    long ans = 0;

    while (n >= d) {
      // find the greatest power of two which when multiplied by d
      // yields number just smaller than n
      int cnt = 0;
      while (n >= (d << (cnt + 1))) {
        cnt++;
      }

      ans += 1 << cnt;

      // update the n
      n -= d << cnt;
    }

    if (ans > Integer.MAX_VALUE) {
      return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    }

    return (int) (sign ? ans : -ans);
  }
}

```
