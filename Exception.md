# Introduction #

This library can throw a lot of different exceptions, each indicating a different problem either with the configuration or with the results of the tests.

All of these Exceptions are `RuntimeExceptions` since this entire library is designed to be run as a unit test. By making them RuntimeExcpeptions, your unit test reports will contain the exact error, message, and stacktrace.

# Exceptions #

## CyclicRedundencyException ##

If the cyclic redundancy test is run (via setting `<cyclicalDependency test="true"/>`) and any cyclic redundancies are found, this exception is thrown.

When any cyclic dependencies are encountered, this exception is thrown. The exception message will report _all_ packages that are involved in cyclic dependencies. An example message looks like:

```
com.seventytwomiles.architecturerules.exceptions.CyclicRedundancyException: 
cylic depencencies found:

	-- test.com.seventytwomiles.services
	¦  ¦
	¦  ¦-- test.com.seventytwomiles.model
	¦  ¦
	¦  ¦-- test.com.seventytwomiles.dao.hibernate
	¦
	¦
	-- test.com.seventytwomiles.model
	¦  ¦
	¦  ¦-- test.com.seventytwomiles.services
	¦
	¦
	-- test.com.seventytwomiles.dao.hibernate
	   ¦
	   ¦-- test.com.seventytwomiles.services	
```

## DependencyConstraintException ##

When any defined violation occurs, this exception is thrown. The message will tell you which package was violated, who who the violating package was. The message (as of the time of this writing) looks like

_Dependency Constraint failed in dao rule which constrains package com.seventytwomiles.pagerank.core.services_


## IllegalArchitectureRuleException ##

`IllegalArchitectureRuleException` is thrown when a rule defines a `violation` in the `package` that it describes.

For example:

```
<rule id="dao">

    <comment>
        The dao interface package should rely on nothing.
    </comment>

    <packages>
        <package>
            com.seventytwomiles.pagerank.core.dao
        </package>     
    </packages>

    <violations>
        <violation>
            com.seventytwomiles.pagerank.core.dao
        </violation>       
    </violations>
</rule>
```

would throw this exception because JDepend can not test that the dao package does not depend on the dao package. An exception is thrown telling you to remove this violation. The violation _could_ be ignored by the tests, but it is not. Users should understand that this can not be tested and remove this violation.

## SourceNotFoundException ##

When a source is defined and the `not-found` property is set to `exception` this `SourceNotFoundException` will be thrown if the source directory does not exist or can not be found.

```
<source not-found="exception">core/target/classes</source>
```

If you are getting this exception and don't want it because you understand that your entire project was not build and so a given source may not exit all the time, then use this configuration:

```
<source not-found="ignore">core/target/classes</source>
```


## NoPackagesFoundException ##

```
<sources no-packages="exception">
```

When no packages are found in any of your `sources` the `NoPackagesFoundException` is thrown. When no packages are found, no tests are run, which can be deceiving when everything comes back looking ok. To disable this exception (which is highly not recommended) set:

```
<sources no-packages="ignore">
```