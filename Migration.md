# Introduction #

How to upgrade


# 1.1 to 2.0-rc1 #

## architecture-rules.xml ##

### move id to the rule element as a property ###

```
<rule>
   <id>dao</id>
</rule>
```

becomes

```
<rule id="dao">
  ...
</rule>
```

The benefit here is now, when you collapse your rules, you can see which id is which.

```

<architecture>

    <configuration ...>

    <rules>

        <rule id="dao" ...>

        <rule id="web" ...>

        <rule id="services" ...>

        <rule id="model" ...>

    </rules>

</architecture>
```


### change package to packages and add package ###

```
<package>
   com.seventytwomiles.pagerank.core.dao
</package>
```

becomes

```
<packages>
    <package>
        com.seventytwomiles.pagerank.core.dao
    </package>    
</packages>
```

## unit tests ##

  * extend AbstractArchitectureRulesConfigurationTest
  * perform any extra programmatic configuration in the constructor
  * implement
    * `abstract String getConfigurationFileName()`
    * `abstract void testArchitecture()`
  * call `doTest()` from within `testArchitecture()`

For more help with writing this new test, see WritingYourTest