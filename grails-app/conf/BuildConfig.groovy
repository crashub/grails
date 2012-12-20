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

        compile('org.crsh:crsh.shell.core:1.2.0-cr3') {
            excludes 'crsh.cmdline', 'derby', 'groovy-all', 'hibernate-entitymanager', 'ivy', 'jline', 'junit',
                     'servlet-api', 'shrinkwrap-api', 'shrinkwrap-impl-base', 'slf4j-api', 'slf4j-jdk14', 'tools'
        }

        compile('org.crsh:crsh.cmdline:1.2.0-cr3') {
            excludes 'junit', 'slf4j-api', 'slf4j-jdk14'
        }

        compile('org.crsh:crsh.shell.embed.spring:1.2.0-cr3') {
            excludes 'crsh.shell.core', 'crsh.shell.telnet', 'junit', 'servlet-api', 'slf4j-api', 'slf4j-jdk14',
                     'spring-beans', 'spring-context', 'spring-core', 'spring-web'
        }

        compile('org.crsh:crsh.shell.ssh:1.2.0-cr3') {
            excludes 'bcprov-jdk16', 'crsh.shell.core', 'junit', 'mina-core', 'slf4j-api', 'slf4j-jdk14',
                     'sshd-core', 'sshd-pam'
        }

        compile('org.apache.sshd:sshd-core:0.6.0') {
            excludes 'bcprov-jdk15', 'commons-httpclient', 'commons-logging', 'ganymed-ssh2', 'jline', 'jsch',
                     'junit', 'jzlib', 'mina-core', 'slf4j-log4j12', 'spring-context', 'tomcat-apr'
        }

        compile('com.jcraft:jsch:0.1.45') {
            excludes 'junit', 'jzlib'
        }

        compile 'com.jcraft:jzlib:1.0.7'

        compile('org.apache.sshd:sshd-pam:0.6.0') {
            excludes 'jpam', 'sshd-core'
        }

        compile 'org.bouncycastle:bcprov-jdk16:1.46'

        compile('org.apache.mina:mina-core:2.0.4') {
            excludes 'easymock', 'easymockclassextension', 'junit', 'slf4j-api', 'slf4j-log4j12'
        }

        compile('net.sf.jpam:jpam:1.1') {
            excludes 'checkstyle', 'checkstyle-optional', 'commons-logging', 'junit'
        }
    }

    plugins {
        build(":release:2.2.0", ":rest-client-builder:1.0.3") {
            export = false
        }
    }
}
