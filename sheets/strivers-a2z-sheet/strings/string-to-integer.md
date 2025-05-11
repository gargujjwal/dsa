---
id: string-to-integer
aliases: []
tags: []
layout: default
title: String to Integer
---

## Solution

```java
class Solution {
    public int myAtoi(String s) {
        long ans = 0;
        boolean numberStarted = false;
        boolean isNegative = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Rule 1: ignore leading whitespaces
            if (c == ' ') {
                if (numberStarted) {
                    break;
                } else {
                    continue;
                }
            }

            // Rule 2: Signedness of a number
            if (c == '-' || c == '+') {
                if (numberStarted) {
                    break;
                } else {
                    isNegative = c == '-';
                    numberStarted = true;
                    continue;
                }
            }

            // Rule 3: Read the number by skipping leading zeroes
            if (Character.isDigit(c)) {
                if (c == '0' && numberStarted && ans == 0) {
                    continue;
                }
                // append the digit to answer
                ans = roundingLogic(ans, Character.digit(c, 10), isNegative);
                numberStarted = true;
            } else {
                // Rule 4: If any non digit number is found then quit
                break;
            }
        }

        return isNegative ? -1 * (int) ans : (int) ans;
    }

    private long roundingLogic(long ans, int newDigit, boolean isNegative) {
        int max = Integer.MAX_VALUE;

        // add the new digit
        ans = ans * 10L + newDigit;

        if (isNegative && ans > max + 1L) {
            ans = Integer.MAX_VALUE + 1L;
        } else if (!isNegative && ans > max) {
            ans = Integer.MAX_VALUE;
        }

        return ans;
    }
}
```
