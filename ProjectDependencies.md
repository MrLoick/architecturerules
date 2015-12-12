# Project Dependencies #


## compile ##

The following is a list of compile dependencies for this project. These dependencies are required to compile and run the application:

| **GroupId**             | **ArtifactId**     | **Version** | **Type** | **Download** |
|:------------------------|:-------------------|:------------|:---------|:-------------|
| commons-digester        | commons-digester   | 1.8         | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/commons-digester/commons-digester/1.8/commons-digester-1.8.jar) |
| jdepend                 | jdepend            | 2.9.1       | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/jdepend/jdepend/2.9.1/jdepend-2.9.1.jar) |
| junit                   | junit              | 3.8.1       | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/junit/junit/3.8.1/junit-3.8.1.jar) |
| xerces                  | xercesImpl         | 2.6.2       | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/xerces/xercesImpl/2.6.2/xercesImpl-2.6.2.jar) |
| xml-apis                | xml-apis           | 1.3.04      | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/xml-apis/xml-apis/1.3.04/xml-apis-1.3.04.jar) |


# Project Transitive Dependencies #

The following is a list of transitive dependencies for this project. Transitive dependencies are the dependencies of the project dependencies.


## compile ##

The following is a list of compile dependencies for this project. These dependencies are required to compile and run the application:

| **GroupId**             | **ArtifactId**      | **Version** | **Type** | **Download** |
|:------------------------|:--------------------|:------------|:---------|:-------------|
| avalon-framework        | avalon-framework    | 4.1.3       | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/avalon-framework/avalon-framework/4.1.4/avalon-framework-4.1.4.jar) |
| commons-beanutils	      | commons-beanutils   | 1.7.0       | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.jar) |
| commons-logging         | commons-logging     | 1.1         | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/commons-logging/commons-logging/1.1/commons-logging-1.1.jar) |
| javax.servlet           | servlet-api         | 2.3         | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/javax/servlet/servlet-api/2.3/servlet-api-2.3.jar) |
| log4j                   | log4j               | 1.2.12      | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/log4j/log4j/1.2.12/log4j-1.2.12.jar) |
| logkit                  | logkit              | 1.0.1       | jar      | [download](ftp://ibiblio.org/pub/packages/maven2/logkit/logkit/1.0.1/logkit-1.0.1.jar) |



# Project Dependency Graph #


## Dependency Tree ##

  * com.seventytwomiles:architecture-rules:jar
    * junit:junit:jar
    * jdepend:jdepend:jar
    * xerces:xercesImpl:jar
    * xml-apis:xml-apis:jar
    * commons-digester:commons-digester:jar
      * commons-beanutils:commons-beanutils:jar
        * commons-logging:commons-logging:jar


## Dependency Listings ##

**architecture-rules**

Leverages an XML configuration file to test your code's architecture via unit tests. Able to assert that specific packages do not depend on each other and able to check for cyclic redundancies among your packages. TEST

**Unnamed - junit:junit:jar:3.8.1**

**Unnamed - jdepend:jdepend:jar:2.9.1**

**Unnamed - xerces:xercesImpl:jar:2.6.2**

**XML Commons External Components XML APIs**

xml-commons provides an Apache-hosted set of DOM, SAX, and JAXP interfaces for use in other xml-based projects. Our hope is that we can standardize on both a common version and packaging scheme for these critical XML standards interfaces to make the lives of both our developers and users easier. The External Components portion of xml-commons contains interfaces that are defined by external standards organizations. For DOM, that's the W3C; for SAX it's David Megginson and sax.sourceforge.net; for JAXP it's Sun.

http://xml.apache.org/commons/components/external/

**Digester**

The Digester package lets you configure an XML->Java object mapping module which triggers certain actions called rules whenever a particular pattern of nested XML elements is recognized.

http://jakarta.apache.org/commons/digester/

Unnamed - commons-beanutils:commons-beanutils:jar:1.7.0

**Logging**

Commons Logging is a thin adapter allowing configurable bridging to other, well known logging systems.

http://jakarta.apache.org/commons/${pom.artifactId.substring(8)}/