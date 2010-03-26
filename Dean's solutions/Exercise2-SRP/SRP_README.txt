Single Responsibility Principle

An object should have one and only one reason to change.

             *   *   *   *   *    *    *   *   *

While writing the function that calculates fines, our tests broke.
This probably has something to do with the fact that there are two
kinds of receipts, loan reciepts, and return receipts which are both
represented by the Receipt class.

See if you can get fines to work properly by making the tests pass.
Then see if you can find a way to prevent this kind of bug from
happening again.