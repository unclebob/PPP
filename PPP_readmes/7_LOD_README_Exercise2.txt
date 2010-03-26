Remember the BookFormatReport back in Exercise5-LSP?  That report 
has been arrested for breaking the Law of Demeter.

Here is the alleged offense:

  public void generate() {
    numberHardCover = 0;
    numberSoftCover = 0;
    List books = gateway.getAllLoanedBooks();
    for (Iterator i = books.iterator(); i.hasNext();) {
      Book book = (Book) i.next();
      if (book.usesFormat(Book.HARD_COVER))
        numberHardCover++;
      else if (book.usesFormat(Book.SOFT_COVER))
        numberSoftCover++;
    }
  }

This is a serious offense because the loop is duplicated code and the complex 
inner structure of the loaned books is revealed.

We have decided on a reform that allows BookFormatReport to 
get out of LoD jail.

Create a CountCheckedOutBooksReporter class that accepts a MediaCopy 
objects one at a time and counts each book type (checked-out 
hardcover and softcover books).

The CountCheckedOutBooksReporter object is passed to the MediaGateway.  The
MediaGateway passes each MediaCopy object to the visitor one at a time.
When the MediaGateway is done with the visitor the report can ask the 
visitor for the results of the query.

Use TDD to develop the visitor and the InMemoryMediaGateway.

Hints:

Create class CountCheckedOutBooksReporter that has a accept method that takes
a MediaCopy.

Create a new method on the MediaGateway called visitAllMedia 
that takes your visitor.
  
Modify BookFormatReport to use the visitor and the visitAllMedia method
All tests should continue to pass

Extract the MediaVisitor interface from the CountCheckedOutBooksVisitor
to generalize your solution.

Your BookFormatReport.generate() method looks like this

  public void generateDesignWithVisitor() {
    CountCheckedOutBooksVisitor visitor = new CountCheckedOutBooksVisitor();
    gateway.visitAllMedia(visitor);
    
    numberHardCover = visitor.getCheckedOutBookHardCoverCount();
    numberSoftCover = visitor.getCheckedOutBookSoftCoverCount();
  }
