---
id: remove-outermost-parenthesis
aliases: []
tags: []
layout: default
title: Remove Outermost Parenthesis
---

## Better Solution

```python
def removeOuterParentheses(s: str) -> str:
    stack: deque[tuple[str, int]] = deque()

    lvl = 0
    new_str: list[str] = []

    for c in s:
        if c == "(":
            stack.append((c, lvl))

            if lvl > 0:
                new_str.append(c)
            lvl += 1
        else:
            _, prev_lvl = stack.pop()
            if prev_lvl > 0:
                new_str.append(c)
            lvl -= 1

    return "".join(new_str)
```

## Optimal Solution

```python
def removeOuterParentheses(s: str) -> str:
    lvl = 0
    new_str: list[str] = []

    for c in s:
        if c == "(":
            if lvl > 0:
                new_str.append(c)
            lvl += 1
        else:
            if lvl - 1 > 0:
                new_str.append(c)
            lvl -= 1

    return "".join(new_str)
```
