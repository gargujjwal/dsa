---
id: word-ladder
aliases: []
tags: []
layout: default
title: Word Ladder
---

## Solution

```java
class Solution {
  private static class Pair {
    String w;
    int lvl;

    public Pair(String w, int lvl) {
      this.w = w;
      this.lvl = lvl;
    }
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) return 0;

    Set<String> wordSet = new HashSet<>(wordList);
    Deque<Pair> q = new ArrayDeque<>();
    q.offer(new Pair(beginWord, 1));
    while (!q.isEmpty()) {
      Pair p = q.poll();
      StringBuilder sb = new StringBuilder(p.w);

      if (endWord.equals(p.w)) return p.lvl;

      // now change each letter and see if it exists in worldList
      for (int i = 0; i < sb.length(); i++) {
        char original = sb.charAt(i);
        for (char c = 'a'; c <= 'z'; c++) {
          sb.replace(i, i + 1, Character.toString(c));

          String s = sb.toString();
          if (wordSet.contains(s)) {
            wordSet.remove(s);
            q.offer(new Pair(s, p.lvl + 1));
          }
        }

        sb.replace(i, i + 1, Character.toString(original));
      }
    }

    return 0;
  }
}
```

## Bidirectional BFS

```java
class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);

    // can't make it to end word if its not in list
    if (!wordSet.contains(endWord)) return 0;

    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();

    beginSet.add(beginWord);
    endSet.add(endWord);
    wordSet.remove(beginWord);
    wordSet.remove(endWord);
    int len = 1;

    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
      // always expand smaller set
      if (beginSet.size() > endSet.size()) {
        Set<String> temp = beginSet;
        beginSet = endSet;
        endSet = temp;
      }

      Set<String> nextLevel = new HashSet<>();

      for (String word : beginSet) {
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
          char original = chars[i];
          for (char c = 'a'; c <= 'z'; c++) {
            if (c == original) continue;

            chars[i] = c;
            String next = new String(chars);

            if (endSet.contains(next)) return len + 1;

            if (wordSet.contains(next)) {
              wordSet.remove(next);
              nextLevel.add(next);
            }
          }
          chars[i] = original;
        }
      }

      beginSet = nextLevel;
      len++;
    }

    return 0;
  }
}
```
