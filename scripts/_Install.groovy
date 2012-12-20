ant.mkdir(dir:"${basedir}/web-app/WEB-INF/crash/commands")
ant.copy(file:"$crashPluginDir/src/groovy/evaluate.groovy", toDir:"${basedir}/web-app/WEB-INF/crash/commands")
