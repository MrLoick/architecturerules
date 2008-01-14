package com.seventytwomiles.architecturerules.services;

/*
 * Copyright 2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information visit
 * http://architecturerules.googlecode.com/svn/docs/index.html
 */


import com.seventytwomiles.architecturerules.configuration.Configuration;
import com.seventytwomiles.architecturerules.domain.Rule;
import com.seventytwomiles.architecturerules.domain.SourceDirectory;
import com.seventytwomiles.architecturerules.exceptions.CyclicRedundancyException;
import junit.framework.TestCase;



/**
 * <p><code>CyclicRedundancyService</code> Tester.</p>
 *
 * @author mikenereson
 */
public class CyclicRedundancyServiceTest extends TestCase {


    private CyclicRedundancyService cyclicRedundancyService;
    private Configuration configuration = new Configuration();
    private final SourceDirectory testClassesSourceDirectory
            = new SourceDirectory("target\\test-classes", true);
    private final Rule controllerRule = new Rule();
    private final Rule modelRule = new Rule();


    public CyclicRedundancyServiceTest(final String name) {
        super(name);
    }


    public void setUp() throws Exception {

        super.setUp();

        configuration.addSource(testClassesSourceDirectory);

        /**
         * Expect that ..web.spring depends on both ..dao and ..dao.hibernate
         */
        controllerRule.setId("controllers");
        controllerRule.addPackage("test.com.seventytwomiles.web.spring");
        controllerRule.addViolation("test.com.seventytwomiles.dao");
        controllerRule.addViolation("test.com.seventytwomiles.dao.hibernate");
        configuration.addRule(controllerRule);

        /**
         * Expect model to have cyclical dependency with services
         */
        modelRule.setId("model");
        modelRule.addPackage("test.com.seventytwomiles.model");
        modelRule.addViolation("test.com.seventytwomiles.services");
        configuration.addRule(modelRule);

        configuration.setThrowExceptionWhenNoPackages(true);

        cyclicRedundancyService = new CyclicRedundancyServiceImpl(
                configuration);
    }


    public void tearDown() throws Exception {

        cyclicRedundancyService = null;
        configuration = null;

        super.tearDown();
    }


    public void testPerformCyclicRedundancyCheck() throws Exception {

        final String expected =
                "cyclic dependencies found:\n" +
                        "\n" +
                        "\t-- com.seventytwomiles.architecturerules.domain\n" +
                        "\t�  �\n" +
                        "\t�  �-- com.seventytwomiles.architecturerules.exceptions\n" +
                        "\t�\n" +
                        "\t�\n" +
                        "\t-- test.com.seventytwomiles.services\n" +
                        "\t�  �\n" +
                        "\t�  �-- test.com.seventytwomiles.model\n" +
                        "\t�  �\n" +
                        "\t�  �-- test.com.seventytwomiles.dao.hibernate\n" +
                        "\t�\n" +
                        "\t�\n" +
                        "\t-- test.com.seventytwomiles.model\n" +
                        "\t�  �\n" +
                        "\t�  �-- test.com.seventytwomiles.services\n" +
                        "\t�\n" +
                        "\t�\n" +
                        "\t-- test.com.seventytwomiles.dao.hibernate\n" +
                        "\t�  �\n" +
                        "\t�  �-- test.com.seventytwomiles.services\n" +
                        "\t�\n" +
                        "\t�\n" +
                        "\t-- com.seventytwomiles.architecturerules.exceptions\n" +
                        "\t�  �\n" +
                        "\t�  �-- com.seventytwomiles.architecturerules.domain\n" +
                        "\t�\n" +
                        "\t";

        try {

            cyclicRedundancyService.performCyclicRedundancyCheck();
            fail("expected CyclicRedundancyException");

        } catch (final CyclicRedundancyException e) {

            final String message = e.getMessage().replaceAll("\r", "");

            // assertTrue(expected.equals(message));

            /**
             * ..services depends on ..model and ..dao.hibernate.
             */
            assertTrue(
                    "expected violation at test.com.seventytwomiles.services",
                    message.indexOf("test.com.seventytwomiles.services") > -1);
            assertTrue("expected violation at test.com.seventytwomiles.model",
                    message.indexOf("test.com.seventytwomiles.model") > -1);
            assertTrue(
                    "expected violation at test.com.seventytwomiles.dao.hibernate",
                    message.indexOf(
                            "test.com.seventytwomiles.dao.hibernate") > -1);
        }
    }
}
