/**
 * Copyright 2007, 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * For more information visit
 *         http://72miles.com/ and
 *         http://architecturerules.googlecode.com/
 */
package org.architecturerules.configuration;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.architecturerules.api.configuration.ConfigurationFactory;
import org.architecturerules.domain.Rule;
import org.architecturerules.domain.SourceDirectory;

import org.springframework.core.io.ClassPathResource;


/**
 * <p>Abstract Factory that provides common functionality for <code>ConfigurationFactory</code> implementations.</p>
 *
 * @author mikenereson
 * @see ConfigurationFactory
 */
public abstract class AbstractConfigurationFactory implements ConfigurationFactory {

    /**
     * <p>To log with. See <tt>log4j.xml</tt>.</p>
     *
     * @parameter log Log
     */
    protected static final Log log = LogFactory.getLog(AbstractConfigurationFactory.class);

    /**
     * <p>Set of Rules read from the configuration file</p>
     *
     * @parameter rules Set
     */
    private final Collection<Rule> rules = new HashSet<Rule>();

    /**
     * <p>Set of  <code>Source</code> read from configuration file</p>
     *
     * @parameter sources Set
     */
    private final List<SourceDirectory> sources = new ArrayList<SourceDirectory>();

    /**
     * <p>Weather or not to throw exception when no packages are found for a given path.</p>
     *
     * @parameter throwExceptionWhenNoPackages boolean
     */
    private boolean throwExceptionWhenNoPackages = false;

    /**
     * <p>Weather or not to check for cyclic dependencies.</p>
     *
     * @parameter doCyclicDependencyTest boolean
     */
    private boolean doCyclicDependencyTest = true;

    /**
     * <p>Fully qualified class name of <code>Listener</code> class implementations to add to the
     * <code>Configuration</code>.</p>
     *
     * @paramerter listeners Set<String> full class name to <code>Listeners</code> implementation
     */
    private final Set<String> includedListeners = new HashSet<String>();

    /**
     * <p>Fully qualified class name of <code>Listener</code> class implementations to remove to the
     * <code>Configuration</code>. This allows you to remove the default Listeners.</p>
     *
     * @paramerter listeners Set<String> full class name to <code>Listeners</code> implementation
     */
    private final Set<String> excludedListeners = new HashSet<String>();

    /**
     * <p>Properties defined by the user or default configuration which can be used by other entities such as
     * <tt>Listener</tt> implementations or services</p>
     *
     * <p>Instantiates to empty properties.</p>
     *
     * @parameter properties Properties
     */
    private Properties properties = new Properties();

    /**
     * <p>Getter for property {@link #excludedListeners}.</p>
     *
     * @return Value for property <tt>excludedListeners</tt>.
     */
    public Set<String> getExcludedListeners() {

        return excludedListeners;
    }


    /**
     * <p>Getter for property {@link #includedListeners}.</p>
     *
     * @return Value for property <tt>includedListeners</tt>.
     */
    public Set<String> getIncludedListeners() {

        return includedListeners;
    }


    /**
     * Getter for property 'properties'.
     *
     * @return Value for property 'properties'.
     */
    public Properties getProperties() {

        return properties;
    }


    /**
     * <p>Getter for property {@link #rules}.</p>
     *
     * @return Value for property <tt>rules</tt>.
     */
    public Collection<Rule> getRules() {

        return this.rules;
    }


    /**
     * <p>Getter for property {@link #sources}.</p>
     *
     * @return Value for property <tt>sources</tt>.
     */
    public List<SourceDirectory> getSources() {

        return this.sources;
    }


    /**
     * <p>Getter for property {@link #doCyclicDependencyTest}.</p>
     *
     * @return Value for property <tt>doCyclicDependencyTest</tt>.
     */
    public boolean isDoCyclicDependencyTest() {

        return doCyclicDependencyTest;
    }


    /**
     * <p>Setter for property {@link #doCyclicDependencyTest}.</p>
     *
     * @param doCyclicDependencyTest Value to set for property <tt>doCyclicDependencyTest</tt>.
     */
    public void setDoCyclicDependencyTest(final boolean doCyclicDependencyTest) {

        this.doCyclicDependencyTest = doCyclicDependencyTest;
    }


    /**
     * <p>Getter for property {@link #throwExceptionWhenNoPackages}.</p>
     *
     * @return Value for property <tt>throwExceptionWhenNoPackages</tt>.
     */
    public boolean isThrowExceptionWhenNoPackages() {

        return throwExceptionWhenNoPackages;
    }


    /**
     * <p>Setter for property {@link #throwExceptionWhenNoPackages}.</p>
     *
     * @param throwExceptionWhenNoPackages Value to set for property <tt>throwExceptionWhenNoPackages</tt>.
     */
    public void setThrowExceptionWhenNoPackages(final boolean throwExceptionWhenNoPackages) {

        this.throwExceptionWhenNoPackages = throwExceptionWhenNoPackages;
    }


    /**
     * @return boolean <tt>true</tt> when <samp>&lt;cyclicalDependency test="true"/> </samp>
     * @see ConfigurationFactory#doCyclicDependencyTest()
     */
    public boolean doCyclicDependencyTest() {

        return doCyclicDependencyTest;
    }


    /**
     * @return boolean <tt>true</tt> when <samp>&lt;sources no-packages="exception"> </samp>
     * @see ConfigurationFactory#throwExceptionWhenNoPackages()
     */
    public boolean throwExceptionWhenNoPackages() {

        return throwExceptionWhenNoPackages;
    }


    /**
     * <p>Adds the given <tt>properties</tt> to this {@link #properties}.</p>
     *
     * @param properties Value to add to property <tt>properties</tt>.
     */
    public void addProperties(final Properties properties) {

        if (properties == null) {

            throw new IllegalArgumentException("properties can not be null");
        }

        Set<Map.Entry<Object, Object>> entries = properties.entrySet();

        for (Map.Entry<Object, Object> property : entries) {

            this.properties.put(property.getKey(), property.getValue());
        }
    }


    /**
     * <p>Read Xml configuration file to String.</p>
     *
     * @param configurationFileName String name of the XML file in the classpath to load and read OR the complete path
     * to the file.
     * @return String returns the contents of the configurationFile
     */
    protected String getConfigurationAsXml(final String configurationFileName) {

        File file = new File(configurationFileName);
        InputStream stream = null;

        if (!file.exists()) {

            /**
             * This code kinda sucks. First, an exception is thrown if the resource
             * does not exist, then an exception could be thrown if the resource
             * could not be read.
             */
            final ClassLoader classLoader = getClass().getClassLoader();

            final ClassPathResource resource = new ClassPathResource(configurationFileName, classLoader);

            if (!resource.exists()) {

                throw new IllegalArgumentException("could not load resource " + configurationFileName + " from classpath. File not found.");
            }

            try {

                stream = resource.getInputStream();
            } catch (IOException e) {

                throw new IllegalArgumentException("could not locate resource " + configurationFileName + " from classpath. File not found.", e);
            }
        }

        try {

            if (stream == null) {

                stream = new FileInputStream(file);
            }

            return IOUtils.toString(stream);
        } catch (final IOException e) {

            final String path = file.getAbsolutePath();

            throw new IllegalArgumentException("could not load configuration from " + path, e);
        }
    }


    /**
     * <p>Validate the configuration.</p>
     *
     * @param configuration String xml content to validate
     * @see "architecture-rules.dtd"
     */
    protected abstract void validateConfiguration(final String configuration);
}
