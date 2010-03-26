Single Responsibility Principle

An object should have one and only one reason to change.

             *   *   *   *   *    *    *   *   *

While writing the function that calculates fines, our tests broke.
This probably has something to do with the fact that there are two
kinds of receipts, loan receipts, and return receipts which are both
represented by the Receipt class.

See if you can get fines to work properly by making the tests pass.
Then see if you can find a way to prevent this kind of bug from
happening again.

* Running the tests results in two failures.  Walk up the stack 
  trace to see where the bug comes from.
  
What is the design problem that led to this bug?

      H I N T S     B E L O W








* The root cause is that receipt is doing two jobs. 

* The Receipt class needs to be split into two separate classes,
  LoanReceipt and BorrowReceipt.

* The OK, LATE, UNBORROWED_BOOK, and UNKNOWN_BOOK types don't apply
  to both types of receipts.  They should be partitioned accordingly.
  You might consider replacing the generic and nonspecific "getStatus"
  and "setStatus" methods that return and set one of these flags with
  a set of methods with more meaningful names like 
    boolean isOK(), 
    void setOK (boolean ok),
    boolean isBookUnknown(),
    etc.

* Some methods of receipt (getDueDate, etc) are only valid for certain
  kinds of receipts.  They should be partitioned.

* Should there be a Receipt class when you are finished?  
  Be ready to defend your position.
