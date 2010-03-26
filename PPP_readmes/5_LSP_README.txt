The Liskov Substitution Principle

The BookFormatReport is a simple report that provides
the number of Books of each format (hardcover and softcover)
that are currently on loan. The librarians use this to
monitor lending trends in order to make decisions about
what formats to stock.

The report is passing all of its tests, but
the librarian has noted that there are a
surprisingly large number of soft cover books listed
in the report, more than have ever been purchased by the library.

The librarian assistant suspects that loaned CDs are somehow being 
counted as books.

Exercise:

1. Write failing tests to reveal the bug.  Are the current tests realistic?

2. Find the bug.

3. Find the LSP violation that is hiding the bug.

4. Eliminate the bug with a quick fix

5. Fix the LSP violation. What is it?

Extra credit:

Add Video Cassette type - needs to have fine if not rewound.

          H I N T S       B E L O W








Write a test that reveals the bug.  The test would include the 
following code segment:

		MediaCopy cd = gateway.addCopy(new CompactDisc("a", "b", "c"));
    cd.setLoaned(new LoanReceipt(null));

Refactoring ideas
 * Get the bug revealing test to pass
   * Cast Media that are Book instances to type Book, and only
     include books in the report.  The bug should vanish.
 * Revelation: Media should not have book only methods and data.
 * Now make it not possible to mistake a CD for a book
   * push format related data and methods down to Book 
     (try Move... and then inline on the HARD_COVER and SOFT_COVER)
 * Part 2: In the report, to get rid of the cast add a method to the MediaGateway
   to get only loaned books (findAllLoanedBooks)
   * Write your tests first!

