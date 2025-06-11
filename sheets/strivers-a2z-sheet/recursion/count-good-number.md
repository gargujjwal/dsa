---
id: count-good-number
aliases: []
tags: []
layout: default
title: Count Good Number
---

## Brute Solution

```java
class Solution {
    private static final int MOD = 1000000007;

    public int countGoodNumbers(long n) {
        long ans = 1;
        for (int i = 0; i < n; i++) {
            int toMultiply = (i & 1) == 0 ? 5 : 4;
            ans = ((ans % MOD) * toMultiply) % MOD;
        }

        return (int) ans;
    }
}
```

## Optimal Solution

```java
class Solution {
    private static final long MOD = 1000000007;

    public int countGoodNumbers(long n) {
        if (n == 1) {
            return 5;
        }
        return (int) ((myPow(5, (n + 1)/2) * myPow(4, n/2)) % MOD);
    }

    private long myPow(long x, long n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        }
        if (isEven(n)) {
            long i = myPow(x, n / 2);
            return ((i % MOD) * (i % MOD)) % MOD;
        } else {
            return ((x % MOD) * myPow(x, n - 1)) % MOD;
        }
    }

    private boolean isEven(long n) {
        return (n & 1) == 0;
    }
}
```
