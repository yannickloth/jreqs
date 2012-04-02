#jreqs

jreqs is a small library that provides methods to implement 1-line runtime checks for requirements in Java applications.

It uses the concept of predicate, inspired by those of [Google Guava](http://code.google.com/p/guava-libraries/ "Guava libraries").

The code of all jreqs modules is copyright of Yannick Loth, Belgium, and is licensed under the *Apache License, Version 2.0*.

## Code example

Nothing's worth an immediate look at the code, so here it is:

```java
...
import biz.littlej.jreqs.Reqs;
import static biz.littlej.jreqs.Reqs.parameterCondition;
import static biz.littlej.jreqs.Reqs.preCondition;
import static biz.littlej.jreqs.Reqs.postCondition;
import biz.littlej.jreqs.predicates.Predicates;
import static biz.littlej.jreqs.predicates.Predicates.notBlankString;
import static biz.littlej.jreqs.predicates.Predicates.existingFile;
import static biz.littlej.jreqs.predicates.PredicateOperations.not;
...
public File createSomeFile(final String fileNameParam) {
    //let's check if the fileNameParam parameter is not a blank String:
    parameterCondition(notBlankString(), fileNameParam, "File name parameter must not be null to create a file.");
    preCondition(not(existingFile()), fileNameParam, "No file with the specified name parameter must exist to create a new file with that name.");
    ...some logic...
    final File file=...create a file...
    Reqs.condition(Predicates.notNull(),file,"The reference to the File instance must not be null."); // See how verbose it is without static import!
    ...some logic...
    postCondition(existingFile(), file, "The file must exist after creation or update."); // With static imports the code is clean and easy to read!
    return file;
}
```

The example above shows the use of all requirement types: parameterCondition, preCondition, postCondition and condition.

## Why

> Because with IT, *everything*, and especially the Devil, is inside the details.

> Because I believe that software craftsmanship is very complex, even to very smart people.

I think one should follow some programming principles, like the following ones (most of which are taken from "The Elements Of Java Style", by Allan Vermeulen et al., ISBN 978-0-521-77768-1, Cambridge University Press):

* Program by contract.
* Use assertions to catch logic errors in your code.
* Use assertions to test pre- and postconditions of a method.
* Use unchecked, run-time exceptions to report serious unexpected errors that may indicate an error in the program's logic.
* Always construct objects in a valid state.
* Document preconditions, postconditions and invariant conditions.
* Fully describe the signature of each method.
* Make your code open for extension, but closed for modification (OCP Principle).
* Throw exceptions early, catch exceptions late.

Checking for condition validity is what it's all about.  That something easy.
But I wanted it to be easy to code: ideally, one line per condition.  Condition checks should be as short as possible
while remaining as complete as possible: we shouldn't pollute logic with tons of checks.

Most people would argue that Java already has assertions.  Well that's fine.  Really.  But you should never forget that
a system administrator may disable assertions in production.  Oh wait!  Production is the single most important
environment, dealing with 'real' information.  As a developer, if I implement various checks in my code, I don't want a
system administrator to be able to disable them.  I even wouldn't want anyone to change anything I've developed (except
for bug fixing or things like this).  I want to ensure that in production, the system will not put itself in an illegal,
unexpected state.

By throwing exceptions as soon as you know that the system won't be able to operate as specified (which means as
soon as you detect a bug using some checks), you make sure:

* that the system won't waste resources trying to do things which you already know will not work.
* that the system puts itself in an unexpected invalid state.
* that you'll be able to trace the origin of the trouble.  If you detect the unexpected state later, maybe too many other
operations will have been executed, which may change the system's state and make it hard to find out the error's origin.

## Usage

### Code example

See the above code example.

### Most important classes

* `biz.littlej.jreqs.Reqs`: defines static requirements methods
* `biz.littlej.jreqs.predicates.Predicates`: defines static methods that return various predicates.
* `biz.littlej.jreqs.predicates.PredicateOperations`: defines static methods that return various predicates consisting in applying logic operations (not, and, nand, nor, or, xor) to one or more predicates.
* `biz.littlej.jreqs.predicates.Predicate`: The predicate interface that you must implement if you want to use your own predicates.  This is the extension point of this library.
* `biz.littlej.jreqs.guava.GuavaPredicateWrapper`: Builds a jreqs predicate which wraps a Google Guava predicate, so you may reuse already existing Guava predicates.

### Most used predicates

These are the most used predicates:

* `biz.littlej.jreqs.predicates.Predicates.notNull()`
* `biz.littlej.jreqs.predicates.Predicates.notBlankString()`
* `biz.littlej.jreqs.predicates.PredicateOperations.not()`
* `biz.littlej.jreqs.predicates.Predicates.notEmptyCollection()`
* `biz.littlej.jreqs.predicates.Predicates.emptyCollection()`
* `biz.littlej.jreqs.predicates.Predicates.isTrue()`
* `biz.littlej.jreqs.predicates.Predicates.isFalse()`

If I had to advise you to use only one predicate, it would be `notNull()`, as most methods expect parameters that must not be null.

### Structure of requirements

The structure of a requirement is constant across the four types:

* You specify a predicate instance
* You specify the object instance on which the predicate will be applied
* You specify the message that will be part of the unchecked exception that is thrown if the predicate doesn't apply (returns false) on the specified object instance

If the system state is fine nothing happens.  If the system state is wrong (some condition is not verified),
an unchecked exception is thrown to indicate the presence of a bug.

### Exceptions that are thrown

* `parameterCondition` throws an `IllegalArgumentException`
* `preCondition` throws a `PreConditionException` (which extends `RequirementException`, which in turn extends `IllegalStateException`)
* `postCondition` throws a `PostConditionException` (which extends `RequirementException`, which in turn extends `IllegalStateException`)
* `condition` throws a `RequirementException` (which extends `IllegalStateException`)

### Exception hierarchy

* `java.lang.RuntimeException`
    * `java.lang.IllegalArgumentException`
    * `java.lang.IllegalStateException`
        * `biz.littlej.jreqs.RequirementException`
            * `biz.littlej.jreqs.PreConditionException`
            * `biz.littlej.jreqs.PostConditionException`

### Shortcuts

The package `biz.littlej.jreqs.predicates` contains various useful predicate classes.  You may build predicates by yourself
(what you'll surely do if you implement your own predicates) or use a shortcut static method from the classes `biz.littlej.jreqs.predicates.Predicates` and `biz.littlej.jreqs.predicates.PredicateOperations`.

The class `biz.littlej.jreqs.Reqs` provides the requirements static methods.

Static import these classes' methods to avoid prepending of `Reqs.` or `Predicates.` everywhere in your code.  That will make the code cleaner and easier to read.

```java
...
import static biz.littlej.jreqs.Reqs.*; // import all static methods from Reqs
import static biz.littlej.jreqs.predicates.Predicates.*; // import all static methods from Predicates
import static biz.littlej.jreqs.predicates.PredicateOperations.not; // only import the not() static method
```

## Artifacts

Each release build creates binary/class, source and javadoc jar files which are deployed to Maven Central repository.
You may find them at this URL: [http://search.maven.org/#search%7Cga%7C1%7Cjreqs](http://search.maven.org/#search%7Cga%7C1%7Cjreqs "Search results on Maven Central repo")

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

## Releases

### 0.1.1

Second release.  Most stuff in the core is unit tested.

Javadoc: [http://littlej.github.com/jreqs/javadoc/0.1.1/](http://littlej.github.com/jreqs/javadoc/0.1.1/ "Javadoc")

### 0.1.0

Initial release, work going on.

## Roadmap

Nothing very precise.  No release schedule has been planned.

Amongst other things:

<table>
  <thead>
    <tr>
      <td><strong>Priority</strong></td>
      <td><strong>Description</strong></td>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>HIGH</td>
      <td>Add a predicate that applies bean validation (JSR 303) to a specified object instance</td>
    </tr>
    <tr>
      <td>MEDIUM</td>
      <td>Implement missing toString(), hashCode() and equals() methods.</td>
    </tr>
    <tr>
      <td>LOW</td>
      <td>Implement missing unit tests (especially for file-predicates)</td>
    </tr>
  </tbody>
<table>

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

I'm using IntelliJ IDEA 11, but as there's nothing IDE-specific in the source code, the project should be editable in any IDE (IntelliJ IDEA, Netbeans, Eclipse, JDeveloper - try to avoid Notepad =D ).
Simply import the projects.

## FAQ (Frequently Asked Questions)

1.  **Why don't you simply use Java's `assert` ?**

    As stated above, assertions may be disabled by system administrators.

1.  **How can I disable (all the checks done with) jreqs ?**

    jreqs is not meant to be disabled.  Use assertions.  Use libraries that don't use jreqs.

1.  **In what kind of methods should I check conditions ?**

    You really should check conditions in *all published methods (public and protected)*.  These are the ones that will be
    (mis-)used by developers who use your code.  These are the entry points into your code.

    If you're a conscientious developer, you hate when people find bugs in your code.  By validating everything that
    is passed to your code, you'll make it *way much harder* to misuse it (I wouldn't say impossible, as in programming nothing's impossible).

1.  **Isn't condition checking wasting lots of resources ?**

    Condition checking *is* using resources.  You have to find the right balance between the risk of putting the system in
    an invalid state and *using* resources to ensure state validity.  I consider that condition checks are not wasting resources, only using them.

    If it's that important to you, some checks may be deferred or depend on the fact that other conditions are verified, so maybe you won't need to execute
    them all at once.  Think about the sequence of condition checks.

1.  **How to request a new feature or to report a bug ?**

    Login to GitHub and create a new issue: [https://github.com/LittleJ/jreqs/issues](https://github.com/LittleJ/jreqs/issues "jreqs issues")

1.  **How can I help ?**

    You're always welcome to help.  See my contact info below.  Make a pull request on GitHub.

1.  **How to get support for jreqs ?**

    jreqs isn't that complex, so there's no support plan.  But if really necessary, I'd be glad to help, feel free to contact me.  I'll be happy to help you in German, French, English and maybe even Dutch (depends on how blonde you are, I really need great motivation to speak Dutch well =D ).

1.  **How can I buy jreqs ?**

    Hmmm.  With lots of money.  See my contact info below.
    (Actually that is not a FAQ, but maybe it's giving ideas to some people... =D)

## About the author of this library

My name is Yannick LOTH.  I'm a Belgian freelance JavaEE/Web expert/architect.

<table>
  <thead>
    <tr>
      <td colspan=2>Web presence</td>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Twitter</td>
      <td>@yannickloth</td>
    </tr>
    <tr>
      <td>Skype</td>
      <td>yannick555</td>
    </tr>
  </tbody>
<table>




