package com.example.litigando.logic;

import java.util.HashMap;
import java.util.Map;

import com.example.litigando.logic.dto.ObjectiveSumResponse;

public class Exercises {
  public static Map<Integer, Integer> FrequencyNumbers(int[] numbers) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    // O(n), recorro el array solo 1 vez
    for (int n : numbers) {
      frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
    
    }

    return frequencyMap;
  }

  public static ObjectiveSumResponse objectiveSum(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < numbers.length; i++) {
        int complement = target - numbers[i];

        if (map.containsKey(complement)) {
            return new ObjectiveSumResponse(map.get(complement), i);
        }

        map.put(numbers[i], i);
    }

    return null; 
  }

  public static void ParenthesesValidator(String text) {
  }
}
