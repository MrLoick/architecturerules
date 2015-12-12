# Introduction #

Some developers wish to assert their architecture, without running their entire test suite. This explains ways to run you ArchitectureTest (without having to run all of your test) for various tools.

Here is how to assert your architecture with

  * Maven
  * Maven 2
  * Ant

# Maven2 #

`$> mvn -Dtest=ArchitectureTest test`


# Maven #

`$> maven -Dtestcase=ArchitectureTest test:single`

> If any maven 1 user confirms that this works, please inform us.

# Ant #

To use the ant goal, you must include your own compile goal. I have added `depends="compile"` to the test-architecture goal, so you need to implement that goal, or change the depends to your own goal, whatever you may have called your compile goal.

Also, you will need to point to all of the libs. If you're an ant user, you probably have a `/libs` directory that you can point to.

```
<project basedir=".">

    <path id="class.path">

        <pathelement id="test classes" path="target\test-classes"/>
        <pathelement id="main classes" path="target\classes"/>
        
        <!-- updated list of dependencies available at http://code.google.com/p/architecturerules/wiki/ProjectDependencies -->
        <pathelement id="commons-digester" path="C:\dev\m2repo\commons-digester\commons-digester\1.8\commons-digester-1.8.jar"/>
        <pathelement id="jdepend" path="C:\dev\m2repo\jdepend\jdepend\2.9.1\jdepend-2.9.1.jar"/>
        <pathelement id="junit" path="c:\dev\m2repo\junit\junit\3.8.1\junit-3.8.1.jar"/>
        <pathelement id="xercesImpl" path="c:\dev\m2repo\xerces\xercesImpl\2.6.2\xercesImpl-2.6.2.jar"/>
        <pathelement id="xml-apis" path="c:\dev\m2repo\xml-apis\xml-apis\1.3.04\xml-apis-1.3.04.jar"/>
        <pathelement id="avalon-framework" path="c:\dev\m2repo\avalon-framework\avalon-framework\4.1.3\avalon-framework-4.1.3.jar"/>
        <pathelement id="commons-beanutils" path="c:\dev\m2repo\commons-beanutils\commons-beanutils\1.7.0\commons-beanutils-1.7.0.jar"/>
        <pathelement id="commons-logging" path="c:\dev\m2repo\commons-logging\commons-logging\1.1\commons-logging-1.1.jar"/>
        <pathelement id="servlet-api" path="c:\dev\m2repo\javax\servlet\servlet-api\2.3\servlet-api-2.3.jar"/>
        <pathelement id="log4j" path="C:\dev\m2repo\log4j\log4j\1.2.12\log4j-1.2.12.jar"/>
        <pathelement id="logkit" path="C:\dev\m2repo\logkit\logkit\1.0.1\logkit-1.0.1.jar"/>
    </path>

    <target name="test-architecture" depends="compile">
        <junit>
            <classpath refid="class.path"/>
            <formatter type="brief" usefile="false"/>
            <test name="com.seventytwomiles.architecturerules.ArchitectureTest" haltonfailure="true"/>
        </junit>
    </target>

</project>
```