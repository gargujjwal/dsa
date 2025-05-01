---
id: four-sum
aliases: []
tags: []
layout: default
title: Four Sum
---

## Brute Solution, T:O(n^4) S:O(1)

- Can avoid a new data structure but I wanted to use it

```python
class Quadruplet:
    __values: Tuple[int, int, int, int]

    def __init__(self, a: int, b: int, c: int, d: int):
        self.__values = tuple(sorted([a, b, c, d]))

    def sum(self) -> int:
        return sum(self.__values)

    def to_list(self) -> List[int]:
        return list(self.__values)

    def __eq__(self, other):
        if not isinstance(other, Quadruplet):
            return False
        return self.__values == other.__values

    def __hash__(self):
        return hash(self.__values)

    def __repr__(self):
        return f"Quadruplet{self.__values}"


def fourSum(arr: List[int], target: int) -> List[List[int]]:
    n = len(arr)
    ans: Set[Quadruplet] = set()

    for i in range(0, n):
        for j in range(i + 1, n):
            for k in range(j + 1, n):
                for l in range(k + 1, n):
                    quad = Quadruplet(arr[i], arr[j], arr[k], arr[l])
                    if quad.sum() == target:
                        ans.add(quad)

    return list(map(lambda q: q.to_list(), ans))
```

## Better Solution, T:O(n^3 log n) S:O(n)

- Replaces the search for fourth element with a hash-set
- Remember that only elements between `j` and `k` can go in hash-set otherwise
  solution won't work

```python
def fourSum(arr: List[int], target: int) -> List[List[int]]:
    n = len(arr)

    ans: Set[Tuple[int, int, int, int]] = set()
    for i in range(0, n):
        for j in range(i + 1, n):
            hash_set: Set[int] = set()
            for k in range(j + 1, n):
                curr_sum = arr[i] + arr[j] + arr[k]
                fourth = target - curr_sum
                if fourth in hash_set:
                    ans.add(tuple(sorted([arr[i], arr[j], arr[k], fourth])))

                hash_set.add(arr[k])

    return list(map(list, ans))
```

## Optimal Solution

- Uses Three Sum Problem's solution

```python
def fourSum(arr: List[int], target: int) -> List[List[int]]:
    n = len(arr)

    arr.sort()
    ans: List[List[int]] = list()
    for i in range(0, n):
        if i != 0 and arr[i] == arr[i - 1]:
            continue

        for j in range(i + 1, n):
            if j != i + 1 and arr[j] == arr[j - 1]:
                continue

            k = j + 1
            l = n - 1
            while k < l:
                curr_sum = arr[i] + arr[j] + arr[k] + arr[l]
                if curr_sum < target:
                    k += 1
                elif curr_sum > target:
                    l -= 1
                else:
                    ans.append([arr[i], arr[j], arr[k], arr[l]])
                    k += 1
                    while k < l and arr[k] == arr[k - 1]:
                        k += 1
                    l -= 1
                    while k < l and arr[l] == arr[l + 1]:
                        l -= 1
    return ans
```
