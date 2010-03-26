The Dependency Inversion Principle

We've added the web interface so go play with the application.
 * Start up the application
   (Instructor has deployment and startup instructions)
 * add a book
 * add a patron
 * check out, and check in a book
   
Dean was riding the train to work today and wanted to work
on the Library application on the train. He started to, but
he couldn't run the tests because the IsbnService was
not able to connect to the internet.

We must be able to run the tests whether or not we have
a live internet connection.  Also the test execution time
has grown and David does not run the unit tests except 
when he goes for coffee, but he thinks it is OK because he
goes for coffee a lot.

We don't have a real database yet, so the various
services and gateways are implemented w/ just enough
functionality to get the application to run, but they
are going to change when we introduce the real implementations.

Also, there are some ugly test-only facilities built into
some of our services.

Exercise:

Before doing anything, change this constructor:

  public MediaCatalog(IsbnService isbnService, CompactDiscService compactDiscService, MediaGateway mediaGateway) {
    this.isbnService = new IsbnService();
    this.compactDiscService = compactDiscService;
    this.mediaGateway = mediaGateway;
  }

to: 
  public MediaCatalog(IsbnService isbnService, CompactDiscService compactDiscService, MediaGateway mediaGateway) {
    this.isbnService = isbnService;
    this.compactDiscService = compactDiscService;
    this.mediaGateway = mediaGateway;
  }

Find where this constructor is called. If the first argument is "null", change it
to "new IsbnService()".


Record how long it takes to run the unit tests.

Refactor the gateways and services such that unit tests can be
run independent of the internet or a database.

Now how long does it take to run the unit tests?  Did it work 
OK on the train?  Is David having withdrawal symptoms?

     H I N T S      B E L O W
















Suggested course of action
 * Define initial problem (hard dependency on IsbnService)
 * Envision a better solution (an interface with two implementors: MockIsbnService & 
   WorldCatIsbnService
 * Figure out the steps to get there with as much green as possible
   * Rename IsbnService to WorldCatIsbnService
   * Extract interface (name=IsbnService, replace where possible=yes)
 * Tests still run, but slowly
 * Create a mock implementation 
   * Name it MockIsbnService
   * Put a map of Isbn/Media pairs
   * Put the mock in the tests where it is needed
	   * Add Isbn/Media pairs to mock to get each test case to pass
	     * You can have a  mock that has a set of canned isbn/book pairs
	     * or you can set up the mock to have just what each test file needs in that
	       files setUp().
	   * If your tests run in under one second you have found all the WorldCat references
	   * if not, look harder for more references (in eclipse show how to find references)
