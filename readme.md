A plugin for grails to include [the Common Reusable SHell (CRaSH)][1]
=====================================================================

# Installation

Just install the plugin with e.g. `grails install-plugin crash` to embed CRaSH into your grails application.
It includes the ssh shell and by default listens on port 2000 with the username/password 'admin'.
So make sure to change that for production!
For general documentation on CRaSH visit the [CRaSH project website][1].

# Configuration

To start up CRaSH, a spring bean named `crashSpringWebBootstrap` of type `SpringWebBootstrap` is defined.
Its config properties can be set by assigning a map of config properties to `plugin.crash.config`, e.g.:

    plugin.crash.config = [
            'crash.ssh.port': 2001,
            'crash.auth.simple.password': 'secretPassword'
    ]

To include telnet support include the following dependency in your own BuildConfig:

    runtime 'org.crsh:crsh.shell.telnet:1.2.5'

# Grails specific usage

There is an `evaluate` command available which takes a `String` as argument which is evaluated as a groovy script.
This script has the [`GrailsApplication`][2] instance available as `grailsApplication` from which pretty much everything grails related should be accessible.

  [1]: http://www.crashub.org/
  [2]: http://grails.org/doc/latest/ref/Controllers/grailsApplication.html
