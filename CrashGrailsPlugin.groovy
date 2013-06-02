import org.crsh.spring.SpringWebBootstrap

class CrashGrailsPlugin {
    def version = "1.2.5"
    def grailsVersion = "2.1 > *"
    def title = "CRaSH Grails Plugin"
    def description = 'Integrates the Common Reusable SHell (CRaSH - http://crashub.org/ ) into Grails.'
    def documentation = "https://github.com/crashub/grails"

    def license = "APACHE"
    def developers = [[name: "Stephan Jaetzold", email: "stephan@jaetzold.de"]]
    def issueManagement = [system: "Github", url: "https://github.com/crashub/grails/issues"]
    def scm = [url: "https://github.com/crashub/grails"]

    def doWithSpring = {
        Properties defaultConfig = [
                'crash.vfs.refresh_period': '1',
                'crash.ssh.port': '2000',
                'crash.telnet.port': '5000',
                'crash.auth': 'simple',
                'crash.auth.simple.username': 'admin',
                'crash.auth.simple.password': 'admin'
        ]
        def conf = new ConfigObject()
        conf.putAll((Map)application.config.plugin.crash.config)
        conf.flatten().each { key, value ->
            defaultConfig.put(key?.toString(), value?.toString())
        }
        // Implement runtime spring config
        crashSpringWebBootstrap(SpringWebBootstrap) {
            config = defaultConfig
        }
    }
}
