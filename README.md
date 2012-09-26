Maven Archetype for a SocialSignin webapp
=======================================

This archetype generates a SocialSignIn webapp, which provides out of the box support for

- <a href="https://github.com/SpringSource/spring-social">spring-social</a>  modules  (Allows connection to 3 party provider APIs)

- <a href="https://github.com/socialsignin/spring-social-security">spring-social-security</a> ( Removes need for local user account management, delegating to 3rd party for login details - no
local usernames or passwords are needed. )

- <a href="https://github.com/socialsignin/socialsignin-provider">socialsignin-provider</a>  modules ( Easy configuration of spring social providers using component scanning )

An example of a webapp created from this archetype is <a href="https://github.com/socialsignin/socialsignin-showcase">socialsignin-showcase</a>

Generating a SocialSignIn webapp from this archetype
----------------------------------------------------

To use this archetype use the mvn archetype:generate plugin:
```
mvn archetype:generate \
-DarchetypeRepository=http://repo.opensourceagility.com/snapshots \
-DarchetypeGroupId=org.socialsignin  \
-DarchetypeArtifactId=mvn-archetype-socialsignin-webapp  \
-DarchetypeVersion=1.0.2-SNAPSHOT \
-DgroupId=com.mygroup \
-DartifactId=myartifactid \
-Dversion=1.0.0-SNAPSHOT 
```
where the groupId,artifactId and version properties are your own project values.

Properties which can be specified for this archetype are the list of providers to be supported, 
the clientIds and secrets for each provider and whether to use the in memory database for quickstart.

By default, support for all 7 providers will be generated (facebook,twitter,lastfm,soundcloud,mixcloud,linkedin and tumblr),
the clientIds and secrets for each one will be "not set" in the environment.properties file and the app will
use an in-memory database for development purposes. 

( Thanks to Sam Douglass at https://github.com/sdouglass for providing spring-social-tumblr module and helping us to showcase it here )

To override these defaults (ie. to change the list of providers supported, to set your client id and secrets for 
your app, or to switch off in-memory database, answer 'N' when asked to confirm the default properties and you
can then enter your chosen values.

Configuring and running the generated webapp
--------------------------------------------

Once you have created your new project using this archetype, you'll need to obtain client API accounts for your chosen
providers and ensure that the client ids and secrets are set in the environment.properties file.  

You will also need to configure the return urls set on each provider account to be http://localhost:8080/ 
(depending on the provider).  The exception to this is for Tumblr where the return url will need to be set to 
http://localhost:8080/signinOrConnect/tumblr

You can then start your project webapp using jetty:

mvn jetty:run

Go to http://localhost:8080/ to run the application


