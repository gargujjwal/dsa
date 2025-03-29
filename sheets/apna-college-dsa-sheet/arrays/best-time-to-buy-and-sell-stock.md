---
id: best-time-to-buy-and-sell-stock
aliases: []
tags: []
---

# Best Time to Buy and Sell Stock

## Brute Solution

Calculate profit from all days starting from a day, compare it and keep the
max profit

```python
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        max_profit = 0

        for i in range(len(prices)):
            for j in range(i + 1, len(prices)):
                profit = prices[j] - prices[i]
                max_profit = max(max_profit, profit)

        return max_profit
```

## Optimal Solution

- We take advantage of the fact that we can't sell before buying,
  so while traversing the array, we keep track of lowest price seen and see the
  profit while while selling it at current price

```python
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        min_price = prices[0]
        max_profit = 0

        for price in prices:
            min_price = min(price, min_price)
            curr_profit = price - min_price
            max_profit = max(curr_profit, max_profit)

        return max_profit
```
