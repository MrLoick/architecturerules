package com.seventytwomiles.architecturerules.configuration;

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


import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 * <p>Abstract Factory that provides common functionality for
 * <code>ConfigurationFactory</code> implementations.</p>
 *
 * @author mikenereson
 * @see ConfigurationFactory
 */
public abstract class AbstractConfigurationFactory implements ConfigurationFactory {


    private static final Log log = LogFactory.getLog(AbstractConfigurationFactory.class);

    /**
     * <p>Set of Rules read from the configuration file</p>
     *
     * @parameter rules Set
     */
    protected final Collection rules = new HashSet();

    /**
     * <p>Set of  <code>Source</code> read from configuration file</p>
     *
     * @parameter sources Set
     */
    protected final List sources = new ArrayList();

    /**
     * <p>Weather or not to throw exception when no packages are found for a
     * given path.</p>
     *
     * @parameter throwExceptionWhenNoPackages boolean
     */
    protected boolean throwExceptionWhenNoPackages = false;

    /**
     * <p>Weather or not to check for cyclic dependencies.</p>
     *
     * @parameter doCyclicDependencyTest boolean
     */
    protected boolean doCyclicDependencyTest = true;

    /**
     * <p>Getter for property {@link #rules}.</p>
     *
     * @return Value for property <tt>rules</tt>.
     */
    public Collection getRules() {
        return rules;
    }

    /**
     * <p>Getter for property {@link #sources}.</p>
     *
     * @return Value for property <tt>sources</tt>.
     */
    public List getSources() {
        return sources;
    }

    // --------------------- Interface ConfigurationFactory ---------------------


    /**
     * @return boolean <tt>true</tt> when <samp>&lt;cyclicalDependency
     *         test="true"/> </samp>
     * @see ConfigurationFactory#doCyclicDependencyTest()
     */
    public boolean doCyclicDependencyTest() {
        return doCyclicDependencyTest;
    }


    /**
     * @return boolean <tt>true</tt> when <samp>&lt;sources
     *         no-packages="exception"> </samp>
     * @see ConfigurationFactory#throwExceptionWhenNoPackages()
     */
    public boolean throwExceptionWhenNoPackages() {
        return throwExceptionWhenNoPackages;
    }


    /**
     * <p>Read Xml configuration file to String.</p>
     *
     * @param configurationFileName String name of the XML file in the classpath
     *                              to load and read
     * @return String returns the contents of the configurationFile
     */
    protected String getConfigurationAsXml(final String configurationFileName) {

        /**
         * This code kinda sucks. First, an exception is thrown if the resource
         * does not exist, then an exception could be thrown if the resource
         * could not be read.
         */
        final ClassLoader classLoader = getClass().getClassLoader();
        final ClassPathResource resource = new ClassPathResource(configurationFileName, classLoader);

        if (!resource.exists())
            throw new IllegalArgumentException("could not load resource "
                    + configurationFileName
                    + " from classpath. File not found.");

        final String xml;

        try {

            xml = FileUtils.readFileToString(resource.getFile(), null);

        } catch (final IOException e) {

            throw new IllegalArgumentException("could not load resource "
                    + configurationFileName
                    + " from classpath. File not found.");
        }

        return xml;
    }


    /**
     * <p>Validate the configuration.</p>
     *
     * @param configuration String xml content to validate
     * @see "architecture-rules.dtd"
     */
    protected abstract void validateConfiguration(final String configuration);
}
