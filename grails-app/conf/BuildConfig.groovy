grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"

if(System.getProperty('build')){
	println "Running with installed plugins..."
} else {
	println "Running with inline plugins happily..."
	grails.plugin.location.gwt="/home/david/Development/Source/opensource/grails-gwt"
}

gwt {
  //home="ivy"
    version="2.4.0"
    gin.version="1.5.0"
//    parallel=true
  dependencies=['com.dawsonsystems:gxt:2.2.5-gwt22','com.dawsonsystems:gxt-multi-upload:0.1']
  //output.path="${basedir}/web-app"
}

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "https://github.com/dawsonsystems/mavenrepo/raw/master/snapshots"
    }
    dependencies {
      compile 'mysql:mysql-connector-java:5.1.18'
      compile 'com.google.inject:guice:3.0'
      compile 'org.apache.commons:commons-lang3:3.1'
        // runtime 'mysql:mysql-connector-java:5.1.13'
      compile ('org.openid4java:openid4java:0.9.6') {
        excludes 'xml-apis', "guice"
      }
    }
    plugins {
      ":weceem:1.1.1"
    }
}
