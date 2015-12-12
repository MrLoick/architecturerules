# Introduction #

This is a description of how to setup an environment to contribute to this project.


# Details #

First, you need to have the following installed... properly.
  * Maven 2.0.7 or 2.x
  * svn

The follow the following steps:
  1. create a folder, something like `c:\dev\svnlocal\architecturerules`
  1. open command window or console and `cd c:\dev\svnlocal\architecturerules`
  1. type `svn checkout http://architecturerules.googlecode.com/svn/trunk/ architecturerules`

|**note** If you plan to make contributions, become a project member prior to checking out, because you'll need to use https:// with a username and password. If you checkout with http:// then decide to contribute, you'll have to delete your local checked out copy and start fresh with an https:// checkout.|
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|


For **IntelliJ** users:
  1. type `mvn idea:idea -DownloadSources=true` _this could take a few minutes if you just installed maven_
  1. close console
  1. open `c:\dev\svnlocal\architecturerules\architecturerules.ipr` with IntelliJ
  1. in IntelliJ goto `settings > version control` and ensure subversion is selected


For **Eclipse** users:
  1. For eclipse users: type `mvn eclipse:eclipse` _this could take a few minutes if you just installed maven_
  1. close console
  1. start eclipse
  1. choose `import > existing project`
  1. navigate to
  1. open `c:\dev\svnlocal\architecturerules\` and select the project file


|**note** My eclipse instructions could be wrong. I don't use it. Please update this page if you find corrections neccessary.|
|:---------------------------------------------------------------------------------------------------------------------------|