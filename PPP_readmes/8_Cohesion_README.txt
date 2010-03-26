Component Cohesion

We're getting close to deploying our application. Once
we do, there is some of our code that will be reusable
by other teams. There is also some of our code that comprises the business logic separate from the code that comprises the web tier.

We'd like to extract a web framework component that is independent of the Library and reusable by other applications.

Examine the web and other library packages with the following questions in mind.

1. What classes are in the wrong packages?

2. What package(s) should the misplaced classes be put into?

3. What additional packages do you think are needed?
