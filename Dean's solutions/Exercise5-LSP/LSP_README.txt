The Liskov Substitution Principle

The BookFormatReport is a simple report that provides
the number of Books of each format (hardcover and softcover)
that are currently on loan. The librarians use this to
monitor lending trends in order to make decisions about
what formats to stock.

The report is passing all of its tests, but
the librarian has noted that there are a
surprisingly large number of soft cover books listed
in the report, more than have ever been purchased by the library.

Exercise:

1. Find the bug.

2. Write failing tests to reveal the bug.  Are the current tests realistic?

3. Find the LSP violation that is hiding the bug.

4. Eliminate the bug by applying the LSP.

Extra credit:

Add Video Cassette type - needs to have fine if not rewound.

