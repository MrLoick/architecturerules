/**
 * Copyright 2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * For more information visit
 *         http://72miles.com and
 *         http://architecturerules.googlecode.com/svn/docs/index.html
 */

package com.seventytwomiles.architecturerules.exceptions;


/**
 * <p>Thrown to indicate that a cyclic redundancy was found.</p>
 *
 * @author mikenereson
 * @see RuntimeException
 */
@SuppressWarnings({"JavaDoc"})
public class CyclicRedundancyException extends RuntimeException {


    /**
     * @see RuntimeException#RuntimeException()
     */
    public CyclicRedundancyException() {
        super("cyclic redundancy");
    }


    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public CyclicRedundancyException(final String message) {
        super(message);
    }


    /**
     * @see RuntimeException#RuntimeException(Throwable)
     */
    public CyclicRedundancyException(final Throwable cause) {
        super("cyclic redundancy", cause);
    }


    /**
     * @see RuntimeException#RuntimeException(String,Throwable)
     */
    public CyclicRedundancyException(final String message,
                                     final Throwable cause) {
        super(message, cause);
    }


    /**
     * <p>Constructs a new CyclicRedundancyException with a generated message
     * containing the given <tt>packageName</tt> and <tt>efferentPackage</tt>.</p>
     *
     * @param packageName String the name of the package containing the cyclic
     * dependency
     * @param efferentPackage String the name of the package the package is
     * cyclicly involved with.
     */
    public CyclicRedundancyException(final String packageName,
                                     final String efferentPackage) {

        super("'{0}' is involved in an cyclically redundant dependency with '{1}'"
                .replaceAll("\\{0}", packageName)
                .replaceAll("\\{1}", efferentPackage));
    }
}
