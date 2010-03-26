The Open-Closed Principle

Software modules should be
- Open to Extension
- Closed to Modification

We'd like to make the following changes to CDs:
- raise the damaged CD fine to $16
- raise the CD late fee to $1.75 per day
- increase the number of days a CD can be loaned to 7

Where do we need to make these changes?
How many different files are involved?
Are they the right ones?

We also want to add a new type of media: Movie/DVD.
The OCP suggests that we should be able to simply
add a new class and NOT modify any existing code.
Can we?

Exercise:

1. Refactor the code so that we can then make the above changes as efficiently as possible. Do not allow your build and tests to break.

2. Using TDD, make the changes described above. Conditional logic concerning the media type should be eliminated.

3. Add the Movie/DVD Media type

Extra Credit:

Add a new type of media: Periodicals.
- should be identified by publication and issue number
- can check out any time you like but they can never leave (due date is "today").

Add AudioBooks as a new kind of media


     H I N T S      B E L O W











Pairing with the instructor:
* Identify the problem (conditional logic, feature envy) and its location 
   DamagedCondition and Library
* Envision a better solution: Media as interface (or abstract class), with 
  CD and Book implementations (or subclasses)
  (Hint: Don't *immediately* extract an interface from Media!)
* Plan the refactoring steps relying on the tool when possible
  * Move conditional logic to Media - Test after each step
    * getLoanPeriod(), getPerDiemFine(), getDamageCharge() now contain the
      conditional logic
  * You should be able to make getTypeCode() protected when all conditional 
    logic is in media
  * Create empty subclasses of Media: Book and CD
  * Find references to the "Media" constructor used for Book, replace
    with Book's constructor.
  * Repeat for CD
  * When you are done you should be able to make Media abstract
  * Get rid of Media type in the super() calls in Book and CD
  * Change the signatures on the constructors to not take a media type
    (one at a time) 
  * Move implementations down to Book and CD (Replace type code with polymorphism)
    * getLoanPeriod(), getPerDiemFine(), getDamageCharge()
    * getAuthor should move to Book
    * author moved to Book Media
    * tests should be added in CompactDisc for Damages, fines, Loan period
    * getAuthor test should be removed from CompactDisc test
  Now you are ready for adding the new feature
  * Movie/DVD should drop right in
  * You should add a test for the Dvd
  * Do you need to add library tests for Dvd?

