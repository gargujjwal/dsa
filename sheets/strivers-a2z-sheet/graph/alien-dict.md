---
id: alien-dict
aliases: []
tags: []
layout: default
title: Alien Dict
---

```java
class Solution {
  public String findOrder(String[] words) {
    // prepare graph
    List<List<Integer>> graph = new ArrayList<>(26);
    for (int i = 0; i < 26; i++) graph.add(new ArrayList<>());

    for (int i = 0; i < words.length - 1; i++) {
      String first = words[i];
      String sec = words[i + 1];

      // invalid lexicographically pairing
      if (first.length() > sec.length() && first.startsWith(sec)) return "";

      int len = Math.min(first.length(), sec.length());
      for (int j = 0; j < len; j++)
        if (first.charAt(j) != sec.charAt(j)) {
          graph.get(first.charAt(j) - 'a').add(sec.charAt(j) - 'a');
          break;
        }
    }

    boolean[] seen = new boolean[26];
    for (String word : words) for (char c : word.toCharArray()) seen[c - 'a'] = true;

    // now do topo sort
    StringBuilder sb = new StringBuilder();
    boolean[] visited = new boolean[26];
    boolean[] visting = new boolean[26];
    for (int i = 0; i < 26; i++) {
      if (!seen[i]) continue;
      if (visited[i]) continue;
      if (!topoDfs(i, graph, visting, visited, sb)) return "";
    }

    return sb.reverse().toString();
  }

  private boolean topoDfs(
      int src, List<List<Integer>> graph, boolean[] visiting, boolean[] visited, StringBuilder sb) {
    if (visiting[src]) return false; // cycle
    if (visited[src]) return true;

    visiting[src] = true;
    for (int v : graph.get(src)) if (!topoDfs(v, graph, visiting, visited, sb)) return false;

    visiting[src] = false;
    visited[src] = true;
    sb.append((char) (src + 'a'));
    return true;
  }
}
```
