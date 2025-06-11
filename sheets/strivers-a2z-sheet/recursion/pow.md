---
id: pow
aliases: []
tags: []
layout: default
title: Pow
---

## Optimal Solution #Recursion

```java
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n == -1) {
            return 1 / x;
        }

        if (n < 0) {
            if (isEven(n)) {
                double i = myPow(x, n / 2);
                return i * i;
            } else {
                return (1 / x) * myPow(x, n + 1);
            }
        } else {
            if (isEven(n)) {
                double i = myPow(x, n / 2);
                return i * i;
            } else {
                return x * myPow(x, n - 1);
            }
        }
    }

    private boolean isEven(int n) {
        return (n & 1) == 0;
    }
}

```
