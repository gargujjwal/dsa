---
id: sort-characters-by-frequency
aliases: []
tags: []
layout: default
title: Sort Characters by Frequency
---

## Using Hashmap and sorting

```java
class Solution {
    class ComparableNode implements Comparable<ComparableNode> {
        char c;
        int freq;

        public ComparableNode(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        @Override
        public int compareTo(Solution.ComparableNode other) {
            return Integer.compare(this.freq, other.freq);
        }

    }

    public String frequencySort(String s) {
        // populate hashmap and calculate frequency
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }

        // create array of freq with their characters to sort it in descending
        // order
        ComparableNode[] freqNodes = new ComparableNode[map.size()];
        int i = 0;
        for (var entry : map.entrySet()) {
            freqNodes[i] = new ComparableNode(entry.getKey(), entry.getValue());
            i++;
        }
        // sort in reverse order
        Arrays.sort(freqNodes, Collections.reverseOrder());

        // convert back to string
        StringBuilder sb = new StringBuilder(s.length());
        for (ComparableNode cn : freqNodes) {
            while (cn.freq > 0) {
                sb.append(cn.c);
                cn.freq--;
            }
        }
        return sb.toString();
    }
}
```
