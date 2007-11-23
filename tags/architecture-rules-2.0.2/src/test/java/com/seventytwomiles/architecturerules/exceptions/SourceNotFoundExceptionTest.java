package com.seventytwomiles.architecturerules.exceptions;

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


import com.seventytwomiles.architecturerules.domain.SourceDirectory;
import junit.framework.TestCase;

import java.util.Collection;
import java.util.HashSet;


/**
 * <code>SourceNotFoundException Tester.</code>
 *
 * @author mikenereson
 */
public class SourceNotFoundExceptionTest extends TestCase {


    public SourceNotFoundExceptionTest(String name) {
        super(name);
    }


    public void testTypicalConstructors() {

        SourceNotFoundException exception;
        String message;
        Throwable cause;


        exception = new SourceNotFoundException();
        message = exception.getMessage();
        cause = exception.getCause();

        assertTrue(message.equals("sources not found"));
        assertEquals(null, cause);


        exception = new SourceNotFoundException("no source classes found");
        message = exception.getMessage();
        cause = exception.getCause();

        assertTrue(message.equals("no source classes found"));
        assertEquals(null, cause);


        exception = new SourceNotFoundException(new IllegalArgumentException());
        message = exception.getMessage();
        cause = exception.getCause();

        assertTrue(message.equals("sources not found"));
        assertTrue(cause instanceof IllegalArgumentException);


        exception = new SourceNotFoundException("no source classes found", new IllegalArgumentException());
        message = exception.getMessage();
        cause = exception.getCause();

        assertTrue(message.equals("no source classes found"));
        assertTrue(cause instanceof IllegalArgumentException);
    }


    public void testInterestingConstructors() {

        SourceNotFoundException exception;
        String message;
        Throwable cause;

        final Collection sourceDirectories = new HashSet();
        sourceDirectories.add(new SourceDirectory("core/target/classes"));
        sourceDirectories.add(new SourceDirectory("util/target/classes"));
        sourceDirectories.add(new SourceDirectory("parent-pom/target/classes"));
        sourceDirectories.add(new SourceDirectory("web/target/classes"));

        exception = new SourceNotFoundException(sourceDirectories);
        message = exception.getMessage();
        cause = exception.getCause();

        assertTrue(message.indexOf("core/target/classes") > -1);
        assertTrue(message.indexOf("util/target/classes") > -1);
        assertTrue(message.indexOf("parent-pom/target/classes") > -1);
        assertTrue(message.indexOf("web/target/classes") > -1);

        assertEquals(null, cause);
    }
}
