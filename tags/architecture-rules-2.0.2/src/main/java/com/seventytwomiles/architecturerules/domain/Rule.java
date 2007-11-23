package com.seventytwomiles.architecturerules.domain;

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
 * For more infomration visit
 * http://architecturerules.googlecode.com/svn/docs/index.html
 */


import com.seventytwomiles.architecturerules.exceptions.IllegalArchitectureRuleException;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


/**
 * <p>Represents a <code>Rule</code> that may not be violoated.</p>
 *
 * @author mikenereson
 */
public class Rule {


    private static final Log log = LogFactory.getLog(Rule.class);

    /**
     * <p>Unique id of this Rule as defined. Used to refer to this Rule in
     * messages.</p>
     *
     * @parameter id String
     */
    private String id;

    /**
     * <p>Collection of Strings. These Strings are package names. The names of
     * the packages that will be check against the {@link #violations}. These
     * packages may NOT depend upon the packages listed in violations.</p>
     *
     * @parameter violations Collection
     */
    private final Collection packages = new HashSet();

    /**
     * <p>Comment about this rule that could be used in messages or just to make
     * the configuration file more readable.</p>
     *
     * @parameter comment String
     */
    private String comment;

    /**
     * <p>Collection of Strings. These Strings are package names. The names of
     * the packages that the {@link #packages} may NOT depend upon</p>
     *
     * @parameter violations Collection
     */
    private final Collection violations = new HashSet();


    /**
     * <p>Constructs a new Rule.</p>
     */
    public Rule() {
        // do nothing
    }


    /**
     * <p>Instanciates a new Rule with the given <tt>id</tt>.</p>
     *
     * @param id sets the {@link #id}
     */
    public Rule(final String id) {
        this.id = id;
    }


    /**
     * <p>Instanciates a new Rule with the given <tt>id</tt>.</p>
     *
     * <p>This constructor is to provide some sense of backwards compatability
     * with the releases in series 1 (1.0 and 1.1)</p>
     *
     * @param id sets the {@link #id}
     * @param packageName a {@link @packages} to assert on.
     */
    public Rule(final String id, String packageName) {
        this.id = id;
        addPackage(packageName);
    }


    /**
     * <p>Getter for property {@link #id}.</p>
     *
     * @return Value for property <tt>id</tt>.
     */
    public String getId() {
        return id;
    }


    /**
     * <p>Getter for property {@link #comment}.</p>
     *
     * @return Value for property <tt>comment</tt>.
     */
    public String getComment() {
        return comment;
    }


    /**
     * <p>Setter for property {@link #comment}.</p>
     *
     * @param comment Value to set for property <tt>comment</tt>.
     */
    public void setComment(final String comment) {

        Assert.assertNotNull("comment can not be null", comment);

        this.comment = comment;
    }


    /**
     * <p>Getter for property {@link #packages}</p>
     *
     * @return Value for property <tt>packages</tt>.
     */
    public Collection getPackages() {
        return packages;
    }


    /**
     * <p></p>
     *
     * @param packageName String
     * @return boolean
     */
    public boolean addPackage(final String packageName) {

        Assert.assertNotNull("null packageName can not be added", packageName);
        Assert.assertFalse("empty packageName can not be added", packageName.equals(""));

        if (violations.contains(packageName))
            throw new IllegalArchitectureRuleException(
                    "Could not add " + packageName + " package because there is already " +
                    "a violation with the same package name for rule " + id);

        return packages.add(packageName);
    }


    /**
     * <p>Remove a violation from this Rule.</p>
     *
     * @param violation String a package this this Rule's package should not
     * test on
     * @return boolean <tt>true</tt> if the violation is renived from the List
     *         of violoations
     */
    public boolean removeViolation(final String violation) {

        Assert.assertNotNull("null violation can not be removed", violation);
        Assert.assertFalse("empty violation can not be removed", violation.equals(""));

        return violations.remove(violation);
    }


    /**
     * <p>Add a new violation to this <code>Rule</code>.</p>
     *
     * @param violation String a package this this Rule's package may NOT depend
     * upon
     * @return boolean <tt>true</tt> if the violation is added to the List of
     *         violoatizons
     */
    public boolean addViolation(final String violation) {

        Assert.assertNotNull("null violation can not be added", violation);
        Assert.assertFalse("empty violation can not be added", violation.equals(""));

        if (packages.contains(violation))
            throw new IllegalArchitectureRuleException(
                    "Could not add architecture rule violation that creates rule " +
                    "that says a package can not use itself. Remove " +
                    "<violation>" + violation + "</violation> " +
                    "from rule " + id);

        return violations.add(violation);
    }


    /**
     * <p>Get all of the violations.</p>
     *
     * <p>Note: this Collection is unmodifiable, use {@link #addViolation} and
     * {@link #removeViolation}</p>
     *
     * @return Collection unmodifiable
     * @throws UnsupportedOperationException when <code>getViolations.add(Object)</code>
     * or <code>getViolations.remove(Object)</code> is called. Use {@link
     * #addViolation} and {@link #removeViolation}.
     */
    public Collection getViolations() {
        return Collections.unmodifiableCollection(violations);
    }


    /**
     * <p>Setter for property {@link #id}.</p>
     *
     * @param id Value to set for property <tt>id</tt>.
     */
    public void setId(final String id) {

        Assert.assertNotNull("id can not be null", id);
        Assert.assertFalse("id can not be empty", id.equals(""));

        this.id = id;
    }


    /**
     * @see Object#equals(Object)
     */
    public boolean equals(final Object object) {

        if (this == object)
            return true;

        if (object == null)
            return false;

        if (!(object instanceof Rule))
            return false;

        final Rule that = (Rule) object;

        if (id != null ? !id.equals(that.getId()) : that.getId() != null)
            return false;

        return true;
    }


    /**
     * @see Object#hashCode()
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }


    /**
     * <p>Describes the properties of this rule in an xml-like format.</p>
     *
     * @return String of xml that describes this <code>Rule</code>.
     */
    public String describe() {
        return describe(false);
    }


    /**
     * <p>Describes the properties of this rule in an xml-like format.</p>
     *
     * @param outputToConsole boolean <tt>true</tt> to output the description to
     * the console
     * @return String of xml that describes this <code>Rule</code>.
     */
    private String describe(final boolean outputToConsole) {

        final StringBuffer stringBuilder = new StringBuffer();
        stringBuilder.append("<rule>").append("\r\n");
        stringBuilder.append("\t").append("<id>").append(id).append("</id>").append("\r\n");
        stringBuilder.append("\t").append("<packages>").append(packages).append("</packages>").append("\r\n");
        stringBuilder.append("\t").append("<comment>").append(comment).append("</comment>").append("\r\n");
        stringBuilder.append("\t").append("<violations>").append("\r\n");

        for (Iterator violationIterator = violations.iterator();
             violationIterator.hasNext();) {

            final String violation = (String) violationIterator.next();
            stringBuilder.append("\t\t").append("<violation>").append(violation).append("</violation>").append("\r\n");
        }

        stringBuilder.append("\t").append("</violations>").append("\r\n");
        stringBuilder.append("</rule>").append("\r\n");

        if (outputToConsole)
            System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }


    public String getDescriptionOfRule() {

        final String ruleDescription = "['{0}' for {1}] "
                .replaceAll("\\{0}", getId())
                .replaceAll("\\{1}", describePackges());

        return ruleDescription;
    }


    public String describePackges() {

        StringBuffer packagesDescription = new StringBuffer();

        final Object[] pacakgesArray = packages.toArray();

        final int totalPackages = pacakgesArray.length;

        for (int i = 0; i < totalPackages; i++) {
            String packageName = (String) pacakgesArray[i];
            packagesDescription
                    .append(packageName.trim())
                    .append(" ");

            if (i + 1 < totalPackages)
                packagesDescription.append(",");
        }

        final String description = packagesDescription.toString()
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");

        return description;
    }
}
