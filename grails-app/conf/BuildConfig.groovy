grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        compile('org.crsh:crsh.shell.embed.spring:1.2.0-cr3') {
            excludes 'crsh.shell.telnet'
        }
        compile 'org.crsh:crsh.shell.ssh:1.2.0-cr3'
    }

    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.2.0",
              ":rest-client-builder:1.0.2") {
            export = false
        }
    }
}
