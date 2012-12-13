import org.crsh.spring.SpringWebBootstrap

class CrashGrailsPlugin {
    // the plugin version
    def version = "1.2.0-cr3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def title = "CRaSH Grails Plugin " // Headline display name of the plugin
    def author = "Stephan Jaetzold"
    def authorEmail = "stephan@jaetzold.de"
    def description = '''\
Integrates the Common Reusable SHell (CRaSH - http://crashub.org/ ) into grails.
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/crashub/grails"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "GITHUB", url: "https://github.com/crashub/grails/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/crashub/grails" ]

    def doWithWebDescriptor = { xml ->
        // Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        def defaultConfig = [
                'crash.vfs.refresh_period': 1,
                'crash.ssh.port': 2000,
                'crash.telnet.port': 5000,
                'crash.auth': 'simple',
                'crash.auth.simple.username': 'admin',
                'crash.auth.simple.password': 'admin'
        ]
        defaultConfig.putAll((Map)application.config.plugin.crash.config)
        // Implement runtime spring config
        crashSpringWebBootstrap(SpringWebBootstrap) {
            config = defaultConfig
        }

    }

    def doWithDynamicMethods = { ctx ->
        // Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        //  Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // Implement code that is executed when the application shuts down (optional)
    }
}
