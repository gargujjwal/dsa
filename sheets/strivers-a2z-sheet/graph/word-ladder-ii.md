---
id: word-ladder-ii
aliases: []
tags: []
layout: default
title: Word Ladder II
---

## Optimal but gives TLE

```java
class Solution {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> ans = new ArrayList<>();
    Set<String> wordSet = new HashSet<>(wordList);

    if (!wordSet.contains(endWord)) return ans;

    Deque<List<String>> q = new ArrayDeque<>();
    q.offer(new ArrayList<>(List.of(beginWord)));

    Set<String> visitedThisLevel = new HashSet<>();
    boolean found = false;

    while (!q.isEmpty() && !found) {
      int size = q.size();
      visitedThisLevel.clear();

      for (int i = 0; i < size; i++) {
        List<String> path = q.poll();
        String currW = path.get(path.size() - 1);

        char[] word = currW.toCharArray();
        for (int j = 0; j < word.length; j++) {
          char original = word[j];

          for (char c = 'a'; c <= 'z'; c++) {
            if (c == original) continue;

            word[j] = c;
            String nextW = new String(word);

            if (!wordSet.contains(nextW)) continue;

            List<String> newPath = new ArrayList<>(path);
            newPath.add(nextW);

            if (nextW.equals(endWord)) {
              ans.add(newPath);
              found = true; // found shortest path — don't go deeper after this level
            } else {
              q.offer(newPath);
              visitedThisLevel.add(nextW);
            }
          }
          word[j] = original;
        }
      }

      // remove all words used in this level from wordSet to prevent revisiting
      wordSet.removeAll(visitedThisLevel);
    }

    return ans;
  }
}
```

## Real Optimal

- Approach

1. BFS for Shortest Path:

   - Use BFS to explore all possible transformations from the beginWord.
   - Maintain a depthMap to record the minimum number of transformations required
     to reach each word.
   - If the endWord is reached during BFS, the search stops, and the depth of the
     endWord is noted.

2. DFS for Path Construction:

   - Once the BFS is complete, use DFS to backtrack from the endWord to the
     beginWord, constructing all valid paths that match the minimum transformation
     depth recorded in depthMap.
   - Each valid path is stored in the result list.

3. Word Transformation:

   - For each word, generate all possible transformations by changing one letter
     at a time and check if the transformed word exists in the wordList.

```java
class Solution {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    // container which acts both as quick lookup for words which would have done
    // using wordList and visited array
    Set<String> wordSet = new HashSet<>(wordList);
    // can form endWord if it ain't in wordList
    if (!wordSet.contains(endWord)) Collections.emptyList();

    // this container stores a reversed graph that is
    // endWord -> prev word ... -> beginWord
    Map<String, Set<String>> reverseGraph = new HashMap<>();

    // begin bfs to find the shortest paths
    Deque<String> queue = new ArrayDeque<>();
    queue.add(beginWord);
    wordSet.remove(beginWord);

    // if shortest path is found, then will toggle it and break early
    // so as to not go beyond shortest path
    boolean foundEnd = false;

    // container to store next level of bfs
    Set<String> nextLevel = new HashSet<>();

    while (!queue.isEmpty()) {
      String currentWord = queue.poll();

      // try to replace each character of word to find a new word which might
      // exist in word list and not seen before
      char[] wordChs = currentWord.toCharArray();
      for (int i = 0; i < wordChs.length; i++) {
        char orgCh = wordChs[i];

        for (char c = 'a'; c <= 'z'; c++) {
          if (c == orgCh) continue;
          wordChs[i] = c;
          String nextWord = new String(wordChs);

          if (wordSet.contains(nextWord)) {
            // if nextWord is present then add to hash otherwise create hashmap
            // then add hash
            reverseGraph.computeIfAbsent(nextWord, k -> new HashSet<>()).add(currentWord);

            // reached last lvl if next word is endword, so can stop iteration
            // to next lvl
            if (nextWord.equals(endWord)) foundEnd = true;
            nextLevel.add(nextWord);
          }
        }
        // restore original string
        wordChs[i] = orgCh;
      }

      // completed this level and now switch to next level if endWord is
      // not found
      if (queue.isEmpty()) {
        if (foundEnd) break;
        // prep q for next lvl
        queue.addAll(nextLevel);
        // all these words are visited now
        wordSet.removeAll(nextLevel);
        // prep for next lvl
        nextLevel.clear();
      }
    }

    // couldn't reach endWord meaning can't form endWord from
    // startword using this list
    if (!foundEnd) return Collections.emptyList();

    // using reverse graph, create all the paths
    List<List<String>> result = new ArrayList<>();
    buildPaths(endWord, beginWord, reverseGraph, result, new ArrayList<>(List.of(endWord)));
    return result;
  }

  private void buildPaths(
      String src,
      String target,
      Map<String, Set<String>> graph,
      List<List<String>> result,
      List<String> currPath) {
    if (src.equals(target)) {
      result.add(new ArrayList<>(currPath.reversed()));
      return;
    }

    for (String prev : graph.getOrDefault(src, Collections.emptySet())) {
      currPath.add(prev);
      buildPaths(prev, target, graph, result, currPath);
      currPath.remove(currPath.size() - 1);
    }
  }
}
```
