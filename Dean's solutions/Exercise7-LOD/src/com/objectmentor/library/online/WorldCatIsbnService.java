package com.objectmentor.library.online;

import com.objectmentor.library.models.Book;
import com.objectmentor.library.services.IsbnService;

import java.io.*;
import java.net.URL;
import java.util.regex.*;

public class WorldCatIsbnService implements IsbnService {
  public Book findBookByIsbn(String isbn) {
    String title = null;
    String author = null;
    try {
      URL url = new URL("http://worldcat.org/search?q=isbn%3A" + isbn);
      InputStream is = url.openStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String line;

      while ((line = br.readLine()) != null) {
        if (line.indexOf("<div class=\"author\"") != -1)
          author = getText(line);
        if (line.indexOf("<div class=\"name\"") != -1)
          title = getText(line);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    if (title == null)
      return null;
    return new Book(isbn, title, author);
  }

  public void clear() {
    //do nothing.
  }

  private String getText(String line) {
    Pattern pattern = Pattern.compile(">(.*?)<");
    Matcher matcher = pattern.matcher(line);
    while (matcher.find()) {
      if (matcher.group(1).length() > 0)
        return matcher.group(1);
    }
    return null;
  }
}
