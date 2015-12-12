# Introduction #

Once your configuration is complete ([SampleConfiguration](SampleConfiguration.md)) you just need to extend `AbstractArchitectureRulesConfigurationTest` and implement the abstract classes that it requires you to implement.

  * abstract String getConfigurationFileName();
  * abstract void testArchitecture();

# Complete examples #

## XML Configuration ##

This example utilizes the xml configuration for 100% of its configuration.

```
public class ArchitectureTest extends AbstractArchitectureRulesConfigurationTest {


    /**
     * @see AbstractArchitectureRulesConfigurationTest
     */
    String getConfigurationFileName() {
        return "architecture-rules.xml";
    }


    /**
     * @see AbstractArchitectureRulesConfigurationTest#testArchitecture()
     */
    public void testArchitecture() {

        /* This could through a multitude of Exceptions */
        assertTrue(doTests());
    }
}
```

## Programmatic Configuration ##

This example also utilizes some programmatic configuration.

```
public class ArchitectureTest extends AbstractArchitectureRulesConfigurationTest {


    public void ArchitectureTest() {

        final Configuration configuration = getConfiguration();

        /**
         * Get the configuration that already has the architecture-rules.xml
         * configuration loaded.
         *
         * Changing a boolean like configuration.setDoCyclicDependencyTest(false)
         * would override the value in the configuration file, because the
         * configuration file is loaded first.
         */

        /* Add another rule */
        final Rule rule = new Rule("test", "test.com.seventytwomiles.dao");
        rule.addViolation("test.com.seventytwomiles.web");
        configuration.addRule(rule);

        /* override the xml configuration to cancel the cyclic dependencytest */
        configuration.setDoCyclicDependencyTest(false);
    }


    /**
     * @see AbstractArchitectureRulesConfigurationTest
     */
    String getConfigurationFileName() {
        return "architecture-rules.xml";
    }


    /**
     * @see AbstractArchitectureRulesConfigurationTest#testArchitecture()
     */
    public void testArchitecture() {

        /* This could through a multitude of Exceptions */
        assertTrue(doTests());
    }
}
```