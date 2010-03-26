The Interface Segragation Principle

Clients should depend only on methods they call.

Currently, the MediaGateway services the BookCatalog
and the CompactDiscCatalog. We want to add support
for Periodicals and Movies. To do so we'll need to
add two new Catalogs and add support for both of
them to the MediaGateway. The problem is that changes
to the MediaGateway will now ripple out to, potentially,
4 different clients.

Exercise:

1.Refactor the code Applying ISP to separate the
	interfaces for BookGateway and CompactDiscGateway.
	
2.Add a new Gateway for Movies.

Extra credit:

Add gateway for periodicals
