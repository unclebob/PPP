package com.objectmentor.library.data;

import java.util.List;

import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.BookTitle;
import com.objectmentor.library.models.Patron;

public interface DataServices {

	// ADD FUNCTIONS---------------------
	/**
	 * Adds a book title
	 * 
	 * @param bt
	 * @return
	 */
	Book addBook(BookTitle bt);

	/**
	 * Adds a patron
	 * 
	 * @param patron
	 * @return
	 */
	void addPatron(Patron patron);

	/**
	 * Finds a book copy using the isbn
	 * 
	 * @param string
	 *            (isbn)
	 * @return
	 */

	// FIND and QUERY METHODS--------------------------------------
	Book findCopy1(String string);

	/**
	 * Returns the number of different books (not individual copies)
	 * 
	 * @return
	 */
	int countBooks();

	/**
	 * Returns all copies of all books
	 * 
	 * @param string
	 *            (isbn)
	 * @return
	 */
	List<Book> findMany(String string);

	/**
	 * Returns true if any books w/ this isbn are stored
	 * 
	 * @param string
	 *            (isbn)
	 * @return
	 */
	boolean canFindCopy(String string);

	/**
	 * Returns the first copy found that is not also borrowed
	 * 
	 * @param string
	 * @return
	 */
	Book findAvailableCopy(String string);

	/**
	 * Finds a book copy using the copy id
	 * 
	 * @param string
	 *            (copy id)
	 * @return
	 */
	Book findCopy2(String string);

	/**
	 * Returns the number of different patrons (not individual copies)
	 * 
	 * @return
	 */
	int countActivePatrons();// TODO - this should return null instead of throw
								// exception

	/**
	 * Finds a patron using the id
	 * 
	 * @param id
	 * @return
	 */
	Patron findPatron(String id);

	/**
	 * Returns a BookTitle object using the submitted String as an ISBN to match
	 * against it
	 * 
	 * @param string
	 * @return
	 */
	BookTitle findTitleByIsbn(String string);
}
