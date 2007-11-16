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
 * For more infomration visit
 * http://architecturerules.googlecode.com/svn/docs/index.html
 */


import com.seventytwomiles.architecturerules.exceptions.CyclicRedundancyException;


/**
 * <p>Interface for the CyclicRedundencyService to provide a contract for
 * implementations to ahear to. This service provices the funcionality
 * neccessary to check for cyclic dependencies.</p>
 *
 * @author mikenereson
 */
public interface CyclicRedundencyService {


    /**
     * <p>Check all the packages in all of the source directories and search for
     * any cyclic redundenc/p>
     *
     * @throws CyclicRedundancyException when cyclic redundency is found
     */
    void performCyclicRedundencyCheck() throws CyclicRedundancyException;
}
