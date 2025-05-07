---
id: reverse-words-in-string
aliases: []
tags: []
layout: default
title: Reverse Words in String
---

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        words = self.extract_words(s)
        words.reverse()
        return " ".join(words)

    def extract_words(self, s: str) -> list[str]:
        all_words: list[str] = list()
        curr_word: list[str] = list()
        for i in s:
            if i == " ":
                if len(curr_word) > 0:
                    word = "".join(curr_word)
                    all_words.append(word)
                    curr_word.clear()
            else:
                curr_word.append(i)

        if len(curr_word) > 0:
            word = "".join(curr_word)
            all_words.append(word)

        return all_words
```
