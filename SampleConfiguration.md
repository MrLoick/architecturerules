# Introduction #

Here is an example. Read on for the break down.


```
<?xml version="1.0"?>

<architecture>

    <configuration>

        <sources no-packages="ignore">
            <source not-found="ignore">core\target\classes</source>
            <source not-found="ignore">util\target\classes</source>
            <source not-found="ignore">parent-pom\target\classes</source>
            <source not-found="ignore">web\target\classes</source>
        </sources>

        <cyclicalDependency test="true"/>

    </configuration>


    <rules>

        <rule id="dao">

            <comment>
                The dao interface package should rely on nothing.
            </comment>

            <packages>
                <package>
                    com.seventytwomiles.pagerank.core.dao
                </package>
                <package>
                    com.seventytwomiles.pagerank.core.dao.hibernate
                </package>
            </packages>

            <violations>
                <violation>
                    com.seventytwomiles.pagerank.core.services
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.core.builder
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.util
                </violation>
            </violations>
        </rule>

        <rule id="strategy">

            <comment>
                Strategies should be as pluggable as possible
            </comment>

            <packages>
                <package>
                    com.seventytwomiles.pagerank.serviceproviders.startegies
                </package>
            </packages>


            <violations>
                <violation>
                    com.seventytwomiles.pagerank.core.services
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.core.dao.hibernate
                </violation>
            </violations>
        </rule>

        <rule id="model">

            <comment>
                Model should remain competely isolated
            </comment>

            <packages>
                <package>
                    com.seventytwomiles.pagerank.core.model
                </package>
            </packages>

            <violations>
                <violation>
                    com.seventytwomiles.pagerank.core.dao
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.core.dao.hibernate
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.core.services
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.core.strategy
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.core.builder
                </violation>
                <violation>
                    com.seventytwomiles.pagerank.util
                </violation>
            </violations>
        </rule>

    </rules>

</architecture>
```


# Breakdown #

## Configuration ##

### Sources ###

```
<sources no-packages="ignore">
    <source not-found="ignore">core\target\classes</source>
    <source not-found="ignore">util\target\classes</source>
    <source not-found="ignore">parent-pom\target\classes</source>
    <source not-found="ignore">web\target\classes</source>
</sources>
```

Sources are the directories that JDepend will analyze. The Jdepend docs call these sources, but in reality you want to point to your complied classes.

`<sources no-packages="ignore">`

Valid `no-packages` values are `ignore` and `exception`.

  * _ignore_ will continue on when the source directory does not exist
  * _exception_ will throw a `SourceNotFoundException` when the directory does not exit.

`<source not-found="ignore">core/target/classes</source>`

Sources can be a relative path or an absolute path. Valid `not-found` values are `ignore` and `exception`.

  * _ignore_ will continue when no packages exist, but nothing will be tested. **Not recommended.**
  * _exception_ will throw a `RuntimeException: No packages found` when no packages are found in the given sources.

### Cyclic Dependency Test ###

```
<cyclicalDependency test="true"/>
```

Easy as `true` or `false` to indicate weather you want this test to run or not. When this test runs and fails, a `CyclicRedundencyException` is thrown.


## Rules ##

Rules define a `package`, and then go on to list each package that would be considered a `violation` to depend on. An `id` is used to refer to the rule when something goes wrong, and a `comment` may be entered in order to document the rule for future developers (or even for yourself for that matter)

```
<rule id="dao">

    <comment>
        The dao interface package should rely on nothing.
    </comment>

    <packages>
        <package>
            com.seventytwomiles.pagerank.core.dao
        </package>
        <package>
            com.seventytwomiles.pagerank.core.dao.hibernate
        </package>
    </packages>

    <violations>
        <violation>
            com.seventytwomiles.pagerank.core.services
        </violation>
        <violation>
            com.seventytwomiles.pagerank.core.builder
        </violation>
        <violation>
            com.seventytwomiles.pagerank.util
        </violation>
    </violations>
</rule>
```

`id`s can not be duplicated, but `package`s can. If you want to break up violations for the same package you can do that.

You can add more than one package to a rule. For example `..dao` and `..dao.hibernate` packages are probably going to be the same, so you can define them in a single rule. **This is a change from the version 1 releases**

You can not add a `violation` that is identical to the `package`. This is because, in reality, if you have a class that uses a class in the same package, no imports are defined, and therefor this situation can not be tested. If you try this, you will get an `IllegalRuleException`

# Conclusion #

Thats all there is to it. You can define as many `rules` as you would like, and `cyclicalDependency`  is not required at all.