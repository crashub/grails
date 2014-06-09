A plugin for grails to include [the Common Reusable SHell (CRaSH)][1]
=====================================================================

# Installation

Add the plugin to your grails plugins in `BuildConfig.groovy` to embed CRaSH into your grails application:

    plugins {
        ...
        compile ":crash:1.3.0-beta1-SNAPSHOT"
        ...
    }


It includes the ssh shell and by default listens on port 2000 with the username/password 'admin'.
So make sure to change that for production!
For general documentation on CRaSH visit the [CRaSH project website][1].

# Configuration

To start up CRaSH, a spring bean named `crashSpringWebBootstrap` of type `org.crsh.grails.GrailsBootstrap` is defined.
Its config properties can be set by assigning a map of config properties to `plugin.crash.config`, e.g.:

    plugin.crash.config = [
            'crash.ssh.port': 2001,
            'crash.auth.simple.password': 'secretPassword'
    ]

To include telnet support include the following dependency in your own BuildConfig:

    runtime 'org.crashub:crash.connectors.telnet:1.3.0-cr7'

# Installation

The plugin install CRaSH commands in the directory `/WEB-INF/crash/commands` of your web application
- the folder `base` contains [base commands][3] such as `jdbc`, `thread`, `jmx`, etc...
- the folder `grails` contains grails commands allowing to interact with the grails runtime

# Adding or modifying commands

Existing commands or new commands can be done at anytime during runtime.

# Switching repl

You can switch to the Groovy repl using the repl command:

    % repl groovy

The [Groovy Repl][4] is similar to the Groovy Shell but can evaluate commands:

    % thread.ls()

You can switch back to the Script repl with the same command but with a Groovy syntax:

    % repl("script")

# Grails specific usage

There are a few commands available specifically developed for Grails:

- `application` management
- `grails` management
- `plugin` management

# Using Grails objects or Spring services

Within a command or in the Groovy repl you can access the contextual attributes:

- `context.attributes.application` is the current [`GrailsApplication`][2]
- `context.attributes.beans` is a map of Spring beans
- `context.attributes.factory` the Spring bean factory

  [1]: http://www.crashub.org/
  [2]: http://grails.org/doc/latest/ref/Controllers/grailsApplication.html
  [3]: http://www.crashub.org/beta/reference.html#_commands_reference
  [4]: http://www.crashub.org/beta/reference.html#_the_groovy_repl
