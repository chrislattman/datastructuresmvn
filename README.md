# Data Structures Maven Project

This project is a Java-only version of [DataStructures](https://github.com/chrislattman/DataStructures) that uses [Maven](https://maven.apache.org/), a popular build automation tool for Java projects maintained by the Apache Software Foundation. It supercedes [Ant](https://ant.apache.org/), which is also maintained by Apache.

Maven makes the software build lifecycle less error-prone. Dependency management is simplified since it uses the [Maven Central Repository](https://repo.maven.apache.org/maven2/), the de facto repository for Java projects, comparable to PyPI for Python projects and npm for JavaScript projects.

A Maven project has at its root folder:

- A `pom.xml` file, which defines dependencies, plugins, etc.
    - pom stands for Project Object Model
- A `src` folder, which includes source code (in `main/`) and test code (in `test/`)
- A `target` folder, which includes generated files (compiled code, documentation, coverage reports, `.jar` files, etc.)
    - This folder is excluded from source control
- An optional `README.txt` file explaining the project (this file, which uses Markdown instead)

The `pom.xml` file is of paramount importance to a Maven project. Dependencies, which are located under the `<dependencies>` element, need the following tags:

- groupId
- artifactId
- version
- scope, which is usually one of: compile, test, runtime
    - Choose the right scope depending on how you use the dependency

Maven utilizes plugins to get things done:

- Maven phases are mapped to plugin:goal pairs; e.g. `compile` maps to `compiler:compile`
    - You can read more [here](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#packaging)

Maven commands defined by this project:

- `mvn checkstyle:checkstyle` runs the [Checkstyle](https://checkstyle.sourceforge.io/) utility
- `mvn compile` compiles the source code into the `target` folder
- `mvn test-compile` runs `mvn compile` and compiles the test code into the `target` folder, but doesn't run unit tests
- `mvn test` runs `mvn test-compile` and runs all unit tests, and produces a [JaCoCo](https://www.jacoco.org/jacoco/index.html) code coverage report
- `mvn package` runs `mvn test` and if all tests pass, creates a `.jar` file of the project in the `target` folder
- `mvn install` runs `mvn package` and installs the created `.jar` file in `~/.m2/repository/`, which is where all Maven dependencies get installed
- `mvn javadoc:javadoc` generates Javadoc documentation of the source code
- `mvn site` builds a static website for the project, including the Checkstyle and Javadoc pages
- `mvn clean` deletes the `target` folder

Maven commands can be combined, e.g. `mvn clean test` forces a clean test run by deleting the `target` folder before running the unit tests.
