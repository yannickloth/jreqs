#jreqs

jreqs is a small library that provides methods to check for requirements at runtime in Java applications.

It uses the concept of predicate, inspired by those of Google Guice.

The code of all jreqs modules is copyright of Yannick Loth, Belgium, and is licensed under the Apache License, Version 2.0.

## Why

I think one should follow some programming principles, like the following ones (taken from "The Elements Of Java Style" (Allan Vermeulen et al., ISBN 978-0-521-77768-1, Cambridge University Press):

* Program by contract.
* Use assertions to catch logic errors in your code.
* Use assertions to test pre- and postconditions of a method.
* Use unchecked, run-time exceptions to report serious unexpected errors that may indicate an error in the program's logic.
* Always construct objects in a valid state.
* Document preconditions, postconditions and invariant conditions.
* Fully describe the signature of each method.
* Make your code open for extension, but closed for modification (OCP Principle).

Checking for condition validity is what it's all about.  That something easy.
But I wanted it to be easy to code: ideally, one line per condition.  Condition checks should be as short as possible
while remaining as complete as possible: we shouldn't pollute logic with tons of checks.

Most people would argue that Java already has assertions.  Well that's fine.  Really.  But you should never forget that
a system administrator may disable assertions in production.  Oh wait!  Production is the single most important
environment, dealing with 'real' information.  As a developer, if I implement various checks in my code, I don't want a
system administrator to be able to disable them.  I even wouldn't want anyone to change anything I've developed (except
for bug fixing or things like this).  I want to ensure that in production, the system will not put itself in an illegal,
unexpected state.

## Usage

### Examples

```java
...
import static biz.littlej.jreqs.Reqs.parameterCondition;
import static biz.littlej.jreqs.Reqs.preCondition;
import static biz.littlej.jreqs.Reqs.postCondition;
import static biz.littlej.jreqs.Reqs.condition;
import static biz.littlej.jreqs.predicates.Predicates.notBlankString;
import static biz.littlej.jreqs.predicates.Predicates.notNull;
import static biz.littlej.jreqs.predicates.Predicates.existingFile;
import static biz.littlej.jreqs.predicates.PredicateOperations.not;
...
public File createSomeFile(final String fileNameParam) {
    parameterCondition(notBlankString(), fileNameParam, "File name parameter must not be null to create a file.");
    preCondition(not(existingFile()), fileNameParam, "No file with the specified name parameter must exist to create a new file with that name.");
    ...some logic...
    final File file=...
    condition(notNull(),file,"The reference to the File instance must not be null.");
    ...some logic...
    postCondition(existingFile(), file, "The file must exist after creation or update.");
    return file;
}
```

The example above shows the use of all requirement types: parameterCondition, preCondition, postCondition and condition.

### Most important classes

* `biz.littlej.jreqs.Reqs`: defines static requirements methods
* `biz.littlej.jreqs.predicates.Predicates`: defines static methods that return various predicates.
* `biz.littlej.jreqs.predicates.PredicateOperations`: defines static methods that return various predicates consisting in applying logic operations (not, and, nand, nor, or, xor) to one or more predicates.
* `biz.littlej.jreqs.predicates.Predicate`: The predicate interface that you must implement if you want to use your own predicates.  This is the extension point of this library.
* `biz.littlej.jreqs.guava.GuavaPredicateWrapper`: Builds a jreqs predicate which wraps a Google Guava predicate, so you may reuse already existing Guava predicates.

### Structure of requirements

The structure of a requirement is constant across the four types:

* You specify a predicate instance
* You specify the object instance on which the predicate will be applied
* You specify the message that will be part of the unchecked exception that is thrown if the predicate doesn't apply (returns false) on the specified object instance

If the system state of the system is fine nothing happens.  If the system state is wrong (some condition is not verified),
an unchecked exception is thrown to indicate the presence of a bug.

### Exceptions that are thrown

* `parameterCondition` throws an `IllegalArgumentException`
* `preCondition` throws a `PreConditionException` (which extends `RequirementException`, which in turn extends `IllegalStateException`)
* `postCondition` throws a `PostConditionException` (which extends `RequirementException`, which in turn extends `IllegalStateException`)
* `condition` throws a `RequirementException` (which extends `IllegalStateException`)

### Exception hierarchy

RuntimeException
> IllegalStateException
> > RequirementException
> > > PreConditionException
> > > PostConditionException
> InvalidArgumentException

## Artifacts

Each release build creates binary/class, source and javadoc jar files which are deployed to Maven Central repository.
You may find them at this URL: [http://search.maven.org/#search%7Cga%7C1%7Cjreqs](http://search.maven.org/#search%7Cga%7C1%7Cjreqs "Search results on Maven Central repo")

## Building from Source

### Prerequisites

* Java 6
* Maven 3
* Git
* A working internet connection

### Checking out the code

Clone the git repository using the URL on the Github home page:

    $ git clone git@github.com:LittleJ/jreqs.git
    $ cd jreqs

### Build on command line

    $ mvn install

## Editing the code in an IDE


I'm using IntelliJ IDEA 11. Simply import the projects. That's it ;)

## Using jreqs in your project

Simply add the following dependency(ies) to your Maven project:

### jreqs core

    <dependency>
      <groupId>biz.littlej.jreqs</groupId>
      <artifactId>jreqs-core</artifactId>
      <version>0.1.1</version>
      <scope>compile</scope>
    </dependency>

### jreqs guava

    <dependency>
      <groupId>biz.littlej.jreqs</groupId>
      <artifactId>jreqs-guava</artifactId>
      <version>0.1.1</version>
      <scope>compile</scope>
    </dependency>

