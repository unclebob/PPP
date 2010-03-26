package com.objectmentor.library.gateways;

import java.util.List;

import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public interface MediaGateway {
  MediaCopy addCopy(Media media);

  MediaCopy findCopy(String isbn);

  int mediaCount();

  List findAllCopies(String isbn);

  boolean contains(String id);

  MediaCopy findAvailableCopy(String isbn);

  MediaCopy findCopyById(String copyId);

	void delete(String id);

	List findAllISBNs();

	List addCopies(Media book, int numberOfNewBooks);
}