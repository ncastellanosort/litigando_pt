package com.example.litigando.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.example.litigando.logic.dto.ObjectiveSumResponse;
import com.example.litigando.logic.dto.ParenthesesValidatorResponse;

public class Exercises {
  public static Map<Integer, Integer> FrequencyNumbers(int[] numbers) {
    // guarddar el numero y la cantidad de veces que aparece
    // { 5: 4}
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    // O(n), recorro el array solo 1 vez
    for (int n : numbers) {
      /* getOrDefault, busca si n ya existe en el map
       * si existe, devuelve el valor de n en ese momento
       * si no existe, devuelve 0
       *
       * luego suma 1 al conteo, el valor que habia + 1, o 0 si no encuentra nada
       * */
      frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
    
    }

    return frequencyMap;
  }

  public static ObjectiveSumResponse objectiveSumOptimized(int[] numbers, int target) {
    // la clave sera el numero que ya vimos, y el valor su indice 
    Map<Integer, Integer> map = new HashMap<>(); 

    for (int i = 0; i < numbers.length; i++) {
      int num = numbers[i];
      int complement = target - num; // es el numero que nos hace falta para llegar al target

      // verificamos si existe el complemento como key
      if (map.containsKey(complement)) {
        // si lo encontramos devolvemos el value del complemento y el indice
        return new ObjectiveSumResponse(map.get(complement), i);
      }

      // si no existe, guardamos el numero actual en el map
      map.put(num, i);
    }

    return null;
  }

  public static ObjectiveSumResponse objectiveSumBrute(int[] numbers, int target) {
    // recorre el indice principal y luego el siguiente
    for (int i = 0; i < numbers.length; i++) {
      for (int j = i + 1; j < numbers.length; j++) {
        // revisa si la suma de los 2 da el target
        if (numbers[i] + numbers[j] == target) {
            return new ObjectiveSumResponse(i, j);
        }
      }
    }

    return null;
  }

  public static ParenthesesValidatorResponse parenthesesValidator(String text) {
    // almacenar los parentesis abiertos
    Stack<Character> stack = new Stack<>();

    /*
     * guardar los parentesis de cierre a apertura, si encontramos uno de cierre,
     ya sabemos cual es su apertura 
     */
    Map<Character, Character> pairs = Map.of(
      ')', '(',
      '}', '{',
      ']', '['
    );

    // convertimos la cadena a un array de chars
    for (char c : text.toCharArray()) {
      // buscamos si existe un parentesis de apertura
      if (pairs.containsValue(c)) {
        // lo agregamos
        stack.push(c);
        
        // si existe un parentesis de cierre
      } else if (pairs.containsKey(c)) {
        /*
         * stack.pop() saca el ultimo elemento del stack
         * y mira si es diferente con el parentesis de apertura esperado para el
         * si no coinciden, estan desbalanceados
        * */
        if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
          /* empty es que no tiene apertura correspondiente
            es como si entrara solo un ), el stack estaria vacio al principio
          */
          return new ParenthesesValidatorResponse(false);
        }
      }
    }

    return new ParenthesesValidatorResponse(stack.isEmpty());
  }
}
