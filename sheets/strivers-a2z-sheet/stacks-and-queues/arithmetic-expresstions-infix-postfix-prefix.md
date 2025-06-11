---
id: arithmetic-expressions-infix-postfix-prefix
aliases: []
tags: []
layout: default
title: Arithmetic Expressions Infix Postfix Prefix
---

## Infix to Postfix Conversion

```java
public class Solution {
  private static final Map<Character, Integer> precedence =
      Map.of(
          '^', 3,
          '*', 2,
          '/', 2,
          '-', 1,
          '+', 1);

  public static String infixToPostfix(String s) {
    StringBuilder ans = new StringBuilder();
    Deque<Character> st = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(') {
        // open bracket goes directly to stack
        st.push(c);
      } else if (Character.isLetterOrDigit(c)) {
        // operand goes directly to ans
        ans.append(c);
      } else if (c == ')') {
        // closed bracket removes all elements in stack until open bracket
        while (st.peek() != '(') ans.append(st.pop());
        // remove open bracket
        st.pop();
      } else {
        // operator
        while (!st.isEmpty()) {
          char top = st.peek();
          // not an operator
          if (!precedence.containsKey(top)) break;
          // current operator has equal or more precedence
          if (precedence.get(c) > precedence.get(top)) break;
          ans.append(st.pop());
        }
        st.push(c);
      }
    }

    // empty stack
    while (!st.isEmpty()) ans.append(st.pop());

    return ans.toString();
  }
}
```

## Infix to Prefix Conversion

```java
public class Solution {
  private static final Map<Character, Integer> precedence =
      Map.of(
          '^', 3,
          '*', 2,
          '/', 2,
          '-', 1,
          '+', 1);

  public static String infixToPrefix(String s) {
    StringBuilder sb = new StringBuilder(s);
    for (int i = 0; i < sb.length(); i++) {
      if (sb.charAt(i) == '(') sb.setCharAt(i, ')');
      if (sb.charAt(i) == ')') sb.setCharAt(i, '(');
    }
    sb.reverse();

    StringBuilder postfix = getSpecialPostfix(sb);
    return postfix.reverse().toString();
  }

  public static StringBuilder getSpecialPostfix(StringBuilder s) {
    StringBuilder ans = new StringBuilder();
    Deque<Character> st = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(') {
        // open bracket goes directly to stack
        st.push(c);
      } else if (Character.isLetterOrDigit(c)) {
        // operand goes directly to ans
        ans.append(c);
      } else if (c == ')') {
        // closed bracket removes all elements in stack until open bracket
        while (st.peek() != '(') ans.append(st.pop());
        // remove open bracket
        st.pop();
      } else {
        // operator
        if (c == '^') {
          while (!st.isEmpty()) {
            char top = st.peek();
            // not an operator
            if (!precedence.containsKey(top)) break;
            // current operator has more precedence
            if (precedence.get(c) > precedence.get(top)) break;
            ans.append(st.pop());
          }
        } else {
          while (!st.isEmpty()) {
            char top = st.peek();
            // not an operator
            if (!precedence.containsKey(top)) break;
            // current operator has equal or more precedence
            if (precedence.get(c) >= precedence.get(top)) break;
            ans.append(st.pop());
          }
        }
        st.push(c);
      }
    }

    // empty stack
    while (!st.isEmpty()) ans.append(st.pop());

    return ans;
  }
}
```

## Postfix to Infix Conversion

- Maintain a stack of strings
- Whenever u read a character u put in stack,
- Whenever u read an operator u remove two elements of stack and put operator
  in between and push it back to stack

```java
public class Solution {
  public String postfixToInfix(String postfix) {
    Deque<StringBuilder> stack = new ArrayDeque<>();
    for (int i = 0; i < postfix.length(); i++) {
      char c = postfix.charAt(i);

      if (Character.isLetterOrDigit(c)) {
        // operand
        var sb = new StringBuilder();
        sb.append(c);
        stack.push(sb);
      } else {
        // operator
        var first = stack.pop();
        var sec = stack.pop();
        // `'(' + sec + operator + first + ')'`
        stack.push(sec.insert(0, '(').append(c).append(first).append(')'));
      }
    }

    return stack.pop().toString();
  }
}
```

## Prefix to Infix Conversion

```java
public String prefixToInfix(String prefix) {
  Deque<StringBuilder> stack = new ArrayDeque<>();
  for (int i = prefix.length() - 1; i >= 0; i--) {
    char c = prefix.charAt(i);

    if (Character.isLetterOrDigit(c)) {
      // operand
      var sb = new StringBuilder();
      sb.append(c);
      stack.push(sb);
    } else {
      // operator
      var first = stack.pop();
      var sec = stack.pop();
      // `'(' + first + operator + sec + ')'`
      stack.push(first.insert(0, '(').append(c).append(sec).append(')'));
    }
  }

  return stack.pop().toString();
}
```

## Postfix to Prefix Conversion

```java
public class Solution {
  public String postfixToPrefix(String postfix) {
    Deque<StringBuilder> stack = new ArrayDeque<>();
    for (int i = 0; i < postfix.length(); i++) {
      char c = postfix.charAt(i);

      if (Character.isLetterOrDigit(c)) {
        // operand
        var sb = new StringBuilder();
        sb.append(c);
        stack.push(sb);
      } else {
        // operator
        var first = stack.pop();
        var sec = stack.pop();
        // `operator + sec + first`
        stack.push(sec.insert(0, c).append(first));
      }
    }

    return stack.pop().toString();
  }
}
```

## Prefix to Postfix Conversion

```java
public class Solution {
  public String prefixToPostfix(String prefix) {
    Deque<StringBuilder> stack = new ArrayDeque<>();
    for (int i = prefix.length() - 1; i >= 0; i--) {
      char c = prefix.charAt(i);

      if (Character.isLetterOrDigit(c)) {
        // operand
        var sb = new StringBuilder();
        sb.append(c);
        stack.push(sb);
      } else {
        // operator
        var first = stack.pop();
        var sec = stack.pop();
        // `first + sec + operator`
        // `operator + sec + first`
        stack.push(first.append(sec).append(c));
      }
    }

    return stack.pop().toString();
  }
}
```
