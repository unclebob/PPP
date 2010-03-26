Component Coupling

the objective 
 * to decide how to decouple the reusable web "framework" packages from the
   library code.

There is already a com.objectmentor.library.web.framework package. Most of the classes
in this package are reusable for other applications, after appropriate refactoring.
Some of the classes may belong in the library application. The other library packages
may also have generic reusable code.

How would you break the following dependency cycles
 * Application <--> Controller
 * Application <--> LibraryRules
 * Models <--> LibraryRules
 * Application --> Services --> Models --> 
             LibraryRules --> Application

Keep in mind that you want the inter-package dependencies going towards abstractions. 
All the dependencies should form a DAG and all should point at packages more stable 
than themselves.

Starting with the framework package, first rename it to 
	com.objectmentor.web.framework. 
(Use the "move" refactoring!) look for library-specific code in the classes and 
refactor them to extract the application-specific information. Find classes in other
packages that should be moved to the framework, creating subpackages as appropriate.
Use the import statements to locate references to library-specific code, etc.

When you are done, this framework should have no dependencies
on the rest of the library code.
 * To get warmed up, start with a package with very few dependencies
 * Then tackle the more difficult cases.

NOTE: this will require more than simply moving files around.  
Some new abstractions and packages will be needed.

