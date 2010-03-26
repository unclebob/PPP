Component Cohesion

We're getting close to deploying our application. Once
we do, there is some of our code that will be reusable
by other teams. There is also some of our code that comprises
the business logic separate from the code that comprises
the web tier.

Exercise:

1. Take a look at the ant build script (build.xml). How is
 	 it dealing with components right now?

2. Consider how to allocate classes to components using
	 the Component Cohesion Principles.

3. Modify the build script to create the appropriate
	 JAR files (one JAR per component).