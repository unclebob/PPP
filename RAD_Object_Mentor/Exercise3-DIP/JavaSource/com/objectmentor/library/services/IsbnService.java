package com.objectmentor.library.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.models.Media;

public class IsbnService {
	//FOR TESTING
  public String isbnLastPassedToFind;

	public Media findBookByIsbn(String isbn) {
		isbnLastPassedToFind = isbn;
		try {
			Thread.sleep(1000);
			long number = Integer.parseInt(isbn);
			return new Media(isbn, ("Title"+number), ("Author"+number), Media.BOOK);
		} catch (InterruptedException ie) {
			throw new IsbnDoesNotExistException();
		} catch (NumberFormatException nfe) {
			return null;
		}
	}
	
	public Media findBookByIsbnUsingRealWorldCatService(String isbn) {
		isbnLastPassedToFind = isbn;
		
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
      return null;
    }
    if (title == null)
      return null;
    return new Media(isbn, title, author, Media.BOOK);
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
