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

1. Refactor the code so that we can then make the above
changes as efficiently as possible. Do not allow your build and 
tests to break.

2. Using TDD, make the changes described above. Conditional logic concerning
the media type should be eliminated.

Extra Credit:

Add a new type of media: Periodicals.
- should be identified by publication and issue number
- can check out any time you like but they can never leave (due date is "today").

Add AudioBooks as a new kind of media

