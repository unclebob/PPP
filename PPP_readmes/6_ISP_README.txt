The Interface Segregation Principle

Clients should depend only on methods they call.

Currently, the MediaGateway services the BookCatalog
and the CompactDiscCatalog. We want to add support
for Periodicals and Movies. To do so we'll need to
add two new Catalogs and add support for both of
them to the MediaGateway. The problem is that changes
to the MediaGateway will now ripple out to, potentially,
4 different clients.

Exercise:

1. Refactor the code Applying ISP to separate the
	interfaces for BookGateway and CompactDiscGateway.
	
2. Add a new Gateway for Movies.

Extra credit:

Add gateway for periodicals

     H I N T S      B E L O W












Refactoring plan hint provided:
 * Diagram the target design diagram
   BookGateway and CdGateway extends MediaGateway
   InMemoryGateway implements BookGateway and CdGateway
 * Create BookGateway and CdGateway, both extends MediaGateway
 * Get rid of interface inheritance of MediaGateway and replace with 
   multiple inheritence of BookGateway and CdGateway
   
   
   
   
   
 * Change references in Catalogs and other places
 * All should still be green
 
 
 
 * Push Book related methods to BookGateway, might start to see failure.  
   Chase them down
 * Rinse, repeat for CdGateway

