The Law (Suggestion) of Demeter

An object should only talk to its immediate neighbors. It should
not reach through its neighbor to the neighbor's neighbor. Doing
so binds the two distant objects and makes them both difficult to
change.

Exercise:

1. Get the failing test to pass.

2. Consider what you had to do to get it to pass. Did you notice
   any LoD violations? If so, refactor the code to eliminate them.
   Whose responsibility is the assignment of the ID?  Right now 
   the client is responsible by default.  The Gateway should
   take responsibility and do its job.  setId() should be removed 
   from everywhere except the PatronGateway.
   
   Hints at the bottom



      H I N T S     B E L O W   (look after 5 minutes of analysis)
          

          
          
          
To get the test to pass, setting the IDs of ALL patrons will result in a passing test.  Not setting all patrons results in NullPointerExceptions.

The PatronGateway should have the responsibility of assigning IDs.  Putting a Patron into the PatronGateway should assign the next ID to the patron and use it for its key.

This is a very baby-step intensive surgery - the tests must be run between each step.

Look for use of setId() to see all the places that call it.  Removing these LoD violations results in more responsible code.


          