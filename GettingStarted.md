# Introduction #

This is a complete tutorial to get you testing your architecture with this architecturerules library.


# Details #

Here are the steps that we'll go over
  * Get the library
  * Write your architecture rules
  * Write your test
  * Run the tests


## Get the library ##

Download the latest from [downloads](http://code.google.com/p/architecturerules/downloads/list)

Ensure you also have the dependencies listed [here](ProjectDependencies.md) in your classpath.

Better yet, if you are maven2 user, download the source via

> `svn checkout http://architecturerules.googlecode.com/svn/trunk/ architecturerules`

and then just run `mvn install` and allow maven to get the dependencies for you.

## Write your architecture rules ##

Create architecture-rules.xml _anywhere_ in your classpath. If you are using maven, I'd suggest `src/test/resources`.

Write your configuration and rules by following this [sample configuration example](SampleConfiguration.md).


## Write your test ##

Create your test class. See [writing your test](WritingYourTest.md) example.

## Run the tests ##

Its a junit test. Run your unit tests however you do that. With your IDE, or with maven via `mvn test`