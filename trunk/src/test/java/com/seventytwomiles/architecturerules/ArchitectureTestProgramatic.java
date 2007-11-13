package com.seventytwomiles.architecturerules;


import com.seventytwomiles.architecturerules.configuration.Configuration;
import com.seventytwomiles.architecturerules.domain.Rule;
import com.seventytwomiles.architecturerules.domain.SourceDirectory;
import com.seventytwomiles.architecturerules.exceptions.DependencyConstraintException;


/**
 * <p>Tests rules. Also tests programatic-only configuration.</p>
 *
 * @author mikenereson
 * @see AbstractArchitectureRulesConfigurationTest
 */
public class ArchitectureTestProgramatic extends AbstractArchitectureRulesConfigurationTest {


    /**
     * <p>Expect this test to fail because presentationRule will be
     * violated</p>
     */
    public void testArchitecture() {

        /* do nothing, this method is required */
    }


    /**
     * <p>Expect this test to pass.</p>
     */
    public void testArchitecture_pass() {

        final Configuration configuration = getConfiguration();

        configuration.addSource(new SourceDirectory("target\\test-classes", true));

        configuration.setDoCyclicDependencyTest(false);
        configuration.setThrowExceptionWhenNoPackages(true);


        final Rule daoRule = new Rule("dao");
        daoRule.setComment("dao may not access presentation.");
        daoRule.addPackage("test.com.seventytwomiles.dao.hibernate");
        daoRule.addViolation("test.com.seventytwomiles.web.spring");

        configuration.addRule(daoRule);


        assertTrue(doTests());
    }


    /**
     * <p>Expect this test to fail because presentationRule will be
     * violated</p>
     */
    public void testArchitecture_fail() {

        final Configuration configuration = getConfiguration();

        configuration.addSource(new SourceDirectory("target\\test-classes", true));

        configuration.setDoCyclicDependencyTest(false);
        configuration.setThrowExceptionWhenNoPackages(true);


        final Rule presentationRule = new Rule("presentation");
        presentationRule.setComment("presentation may not access dao directly.");
        presentationRule.addPackage("test.com.seventytwomiles.web.spring");
        presentationRule.addViolation("test.com.seventytwomiles.dao");
        presentationRule.addViolation("test.com.seventytwomiles.dao.hibernate");

        configuration.addRule(presentationRule);


        try {

            assertTrue(doTests());
            fail("expected DependencyConstraintException");

        } catch (Exception e) {

            assertTrue(e instanceof DependencyConstraintException);
        }
    }


    /**
     * <p>This test has more than one rule, some that are valid and some that
     * are invalid. This test ensures that a failure is detected and
     * reported.</p>
     */
    public void testArchitecture_mixtureButFails() {

        final Configuration configuration = getConfiguration();

        configuration.addSource(new SourceDirectory("target\\test-classes", true));

        configuration.setDoCyclicDependencyTest(false);
        configuration.setThrowExceptionWhenNoPackages(true);

        final Rule daoRule_notViolated = new Rule("dao");
        daoRule_notViolated.setComment("dao may not access presentation.");
        daoRule_notViolated.addPackage("test.com.seventytwomiles.dao.hibernate");
        daoRule_notViolated.addViolation("test.com.seventytwomiles.web.spring");

        configuration.addRule(daoRule_notViolated);

        final Rule presentationRule_violated = new Rule("presentation");
        presentationRule_violated.setComment("presentation may not access dao directly.");
        presentationRule_violated.addPackage("test.com.seventytwomiles.web.spring");
        presentationRule_violated.addViolation("test.com.seventytwomiles.dao");
        presentationRule_violated.addViolation("test.com.seventytwomiles.dao.hibernate");

        configuration.addRule(presentationRule_violated);


        try {

            assertTrue(doTests());
            fail("expected DependencyConstraintException");

        } catch (Exception e) {

            assertTrue(e instanceof DependencyConstraintException);
            assertTrue(e.getMessage().contains("test.com.seventytwomiles.web.spring"));
            assertTrue(e.getMessage().contains("test.com.seventytwomiles.dao.hibernate"));
        }
    }
}
