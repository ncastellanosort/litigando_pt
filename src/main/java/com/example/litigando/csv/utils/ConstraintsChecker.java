package com.example.litigando.csv.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ConstraintsChecker {
  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  public static boolean checkEmail(String email) {
   if (email == null) return false;
        Matcher m = EMAIL_PATTERN.matcher(email);
        return m.matches();
  }
}
