package com.objectmentor.library.utils;

import java.util.*;

public class StringUtil {
  public static String join(List strings, String delimiter) {
    if (strings.isEmpty())
      return "";

    Iterator i = strings.iterator();
    StringBuffer joined = new StringBuffer((String) i.next());

    while (i.hasNext()) {
      String eachLine = (String) i.next();
      joined.append(delimiter);
      joined.append(eachLine);
    }

    return joined.toString();
  }

  public static String chopAtDelimiter(String string, String delimiter) {
    int indexOfDelimiter = string.indexOf(delimiter);
    if (indexOfDelimiter > 0)
      return string.substring(0, indexOfDelimiter);
    else
      return string;
  }

  public static String capitalizeWord(String word) {
    char firstLetter = word.charAt(0);
    firstLetter = Character.toUpperCase(firstLetter);
    return Character.toString(firstLetter) + word.substring(1);
  }
}