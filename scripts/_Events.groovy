eventCreateWarStart = { warName, stagingDir ->
  def buildNumber = System.getenv('BUILD_NUMBER')
  def branch = System.properties['BRANCH']
	if(buildNumber) {
	  ant.propertyfile(file:"${stagingDir}/WEB-INF/classes/application.properties") {
	    entry(key:'system.build', value: buildNumber)
            entry(key:'system.branch', value: branch)
	  }
	}
}

