package test.com.seventytwomiles.services;


import test.com.seventytwomiles.model.Person;

import java.util.Collection;


/**
 * <p>todo: javadocs</p>
 *
 * @author mikenereson
 */
public interface PersonService {


    void createPerson(final Person person);


    Collection loadPersons();
}
