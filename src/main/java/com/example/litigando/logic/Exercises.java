package com.example.litigando.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.example.litigando.logic.dto.ObjectiveSumResponse;
import com.example.litigando.logic.dto.ParenthesesValidatorResponse;

public class Exercises {
  public static Map<Integer, Integer> FrequencyNumbers(int[] numbers) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    // O(n), recorro el array solo 1 vez
    for (int n : numbers) {
      frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
    
    }

    return frequencyMap;
  }

  public static ObjectiveSumResponse objectiveSumOptimized(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>(); 

    for (int i = 0; i < numbers.length; i++) {
      int num = numbers[i];
      int complement = target - num;

      if (map.containsKey(complement)) {
        return new ObjectiveSumResponse(map.get(complement), i);
      }

      map.put(num, i);
    }

    return null;
  }

  public static ObjectiveSumResponse objectiveSumBrute(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
      for (int j = i + 1; j < numbers.length; j++) {
          if (numbers[i] + numbers[j] == target) {
              return new ObjectiveSumResponse(i, j);
          }
      }
    }

    return null;
  }

  public static ParenthesesValidatorResponse parenthesesValidator(String text) {
      Stack<Character> stack = new Stack<>();
      Map<Character, Character> pairs = Map.of(
              ')', '(',
              '}', '{',
              ']', '['
      );

      for (char c : text.toCharArray()) {
        if (pairs.containsValue(c)) {
          stack.push(c);
        } else if (pairs.containsKey(c)) {
          if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
              return new ParenthesesValidatorResponse(false);
          }
        }
      }

      return new ParenthesesValidatorResponse(stack.isEmpty());
  }
}
