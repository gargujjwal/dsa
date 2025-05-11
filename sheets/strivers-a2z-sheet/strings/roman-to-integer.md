---
id: roman-to-integer
aliases: []
tags: []
layout: default
title: Roman to Integer
---

## Roman to Integer

```java
class Solution {
    private static Map<Character, Integer> roman = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);

    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == s.length() - 1) {
                ans += roman.get(c);
                continue;
            }
            // next character to c
            char d = s.charAt(i + 1);

            // special rules
            if (c == 'I')
                if (d == 'V') {
                    ans += 4;
                    i++;
                } else if (d == 'X') {
                    ans += 9;
                    i++;
                } else
                    ans += roman.get(c);
            else if (c == 'X')
                if (d == 'L') {
                    ans += 40;
                    i++;
                } else if (d == 'C') {
                    ans += 90;
                    i++;
                } else
                    ans += roman.get(c);
            else if (c == 'C')
                if (d == 'D') {
                    ans += 400;
                    i++;
                } else if (d == 'M') {
                    ans += 900;
                    i++;
                } else
                    ans += roman.get(c);
            else
                ans += roman.get(c);
        }

        return ans;
    }
}
```

## Integer to Roman

```java
class Solution {
    private static Map<Integer, String> roman = new HashMap<>();
    static {
        roman.put(0, "");
        roman.put(1, "I");
        roman.put(4, "IV");
        roman.put(9, "IX");
        roman.put(5, "V");
        roman.put(10, "X");
        roman.put(40, "XL");
        roman.put(90, "XC");
        roman.put(50, "L");
        roman.put(100, "C");
        roman.put(400, "CD");
        roman.put(900, "CM");
        roman.put(500, "D");
        roman.put(1000, "M");
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int tenPow = 1;
        while (num != 0) {
            int lastDig = num % 10;
            int eqNum = lastDig * tenPow;

            if (roman.containsKey(eqNum)) {
                sb.append(reverse(roman.get(eqNum)));
            } else {
                int closestNum = findClosestNum(eqNum);
                String romanForClosestNum = intToRoman(eqNum - closestNum);
                sb.append(reverse(romanForClosestNum));
                sb.append(reverse(roman.get(closestNum)));
            }

            tenPow *= 10;
            num /= 10;
        }

        return sb.reverse().toString();
    }

    private static StringBuilder reverse(String s) {
        return new StringBuilder(s).reverse();
    }

    // finds closest smaller number in roman map
    private static int findClosestNum(int eqNum) {
        int closestNum = 0;
        int diff = Integer.MAX_VALUE;
        for (var entry : roman.entrySet()) {
            int currDiff = eqNum - entry.getKey();
            if (currDiff < 0) {
                continue;
            }

            if (currDiff < diff) {
                closestNum = entry.getKey();
                diff = currDiff;
            }
        }

        return closestNum;
    }
}
```
