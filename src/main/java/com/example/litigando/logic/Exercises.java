package com.example.litigando.logic;

import java.util.HashMap;
import java.util.Map;

public class Exercises {
  public static Map<Integer, Integer> FrequencyNumbers(int[] numbers) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    // O(n), recorro el array solo 1 vez
    for (int n : numbers) {
      frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
    
    }

    return frequencyMap;
  }

  public static void ObjectiveSum(int[] numbers, int target) {
  }

  public static void ParenthesesValidator(String text) {
  }
}
