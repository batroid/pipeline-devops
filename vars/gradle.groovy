

def call(stageOptions) {

	if (stageOptions.contains('Build') || stageOptions.contains('Test') || stageOptions == ''){
		stage('Build & Test') {
        		sh './gradlew clean build'
    		}
	}
    
	if (stageOptions.contains('Sonar') || stageOptions == ''){
		stage('Sonar') {
		      	echo 'ejecutando sonar'
        }
    }
		
	if (stageOptions.contains('Run') || stageOptions == ''){
		stage('Run') {
        		sh './gradlew bootRun &'
        		sh 'sleep 20'
    		}
	}
		
	if (stageOptions.contains('Rest') || stageOptions == ''){
 		stage('Rest') {
			echo 'ejecutando llamada REST'
        		//sh 'curl -X GET "http://localhost:8888/rest/mscovid/test?msg=testing"'
    		}
	}
		
	if (stageOptions.contains('Nexus') || stageOptions == ''){
		stage('Nexus') {
		      	echo 'ejecutando Nexus'
    		}
	}

}
	
return this;

