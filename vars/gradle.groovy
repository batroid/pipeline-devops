/*
    forma de invocación de método call:
    def ejecucion = load 'script.groovy'
    ejecucion.call()
*/

def call(stage) {

	if (stage.contains('Build') || stage.contains('Test') || stage == ''){
		stage('Build & Test') {
	        	env.TASK = env.STAGE_NAME
        		sh './gradlew clean build'
    		}
	}
    
	if (stage.contains('Sonar') || stage == ''){
		stage('Sonar') {
        		env.TASK = env.STAGE_NAME
		        //corresponde al scanner configurado en global tools Jenkins
        		def scannerHome = tool 'sonar-scanner'
        		//corresponde a lo configurado en sistema Jenkins
        		withSonarQubeEnv('sonar-server') {
            			bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
        	}
    	}
		
	if (stage.contains('Run') || stage == ''){
		stage('Run') {
        		env.TASK = env.STAGE_NAME
        		sh './gradlew bootRun &'
        		sh 'sleep 20'
    		}
	}
		
	if (stage.contains('Run') || stage == ''){
 		stage('Run') {
        		env.TASK = env.STAGE_NAME
        		sh 'curl -X GET "http://localhost:8888/rest/mscovid/test?msg=testing"'
    		}
	}
		
	if (stage.contains('Run') || stage == ''){
		stage('Nexus') {
        		env.TASK = env.STAGE_NAME
		        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'C:/Proyectos/ejemplo-gradle/build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
    		}
	}

}
