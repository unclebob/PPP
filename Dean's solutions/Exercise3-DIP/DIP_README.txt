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

Record how long it takes to run the unit tests.

Refactor the gateways and services such that unit tests can be
run independent of the internet or a database.

Now how long does it take to run the unit tests?  Did it work 
OK on the train?  Is David having withdrawal symptoms?
