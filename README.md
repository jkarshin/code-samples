# code-samples

## Overview

Each project contains a code problem (or several related code problems), one or more solution implementations, and tests for those solutions. The general pattern for each project is as follows:

### Solution Interface

An interface is provided which defines the problem via Javadocs. The interface specifies a single method that will be used to apply the solution to a given input.

### Tests

Tests are written against the interface so that different solution implementations can be run against a single set of tests. Test cases are provided via JUnit 5 parameterized tests.

### Solution Implementation(s)

Each solution implementation provides a javadoc describing its behavior at a high level (except for a few of the standard sorting algorithms). In addition, comments are provided throughout the implementation code detailing the reasoning behind non-obvious subroutines and code segments.

## Checking out the Code

(This project was built against Java 12 on Eclipse 4.11.0)

After cloning the repo, the Gradle wrapper can be used to generate Eclipse project files.

On Windows:
```
gradlew.bat eclipse
```

On Mac/Linux:
```
./gradlew eclipse
```

Once generated, the project files can be imported in Eclipse by doing the following:

1. Right-click within the Package Explorer
2. Select "Import..."
3. Choose "Existing Projects into Workspace" and select "Next"
4. In "Select root directory", browse to the root directory of the cloned repo
5. Verify that the projects appear in the "Projects" section and select "Finish"

## Running Tests

Tests can be run manually through Eclipse or through Gradle. To run the tests through Gradle, simply execute the "test" task:

On Windows:
```
gradlew.bat test
```

On Mac/Linux:
```
./gradlew test
```
