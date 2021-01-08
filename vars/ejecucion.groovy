def call(){
pipeline {
    agent any
    
    stages {
        stage('Pipeline') {
            steps {
                script {
                sh 'env'
                env.TAREA = '' 
                echo "-RUNNING ${env.BUILD_ID} on ${env.JENKINS_URL}" 
                echo "-GIT_BRANCH ${env.GIT_BRANCH}"   

                                          
                if (env.GIT_BRANCH == "develop" || env.GIT_BRANCH == "feature"){
                        gradleci.call();
                } else if (env.GIT_BRANCH.contains("release")){  
                        gradlecd.call();                 
                } else {
                    echo " La rama <${env.GIT_BRANCH}> no se proceso" 
                }

                }
            }
        }
    }


    /*post {
        success{
            slackSend color: 'good', message: "[Andres Aldana][${env.JOB_NAME}][${env.GIT_BRANCH}]Ejecucion exitosa"           
        }

        failure{
            slackSend color: 'danger', message: "[Andres Aldana][${env.JOB_NAME}][${env.GIT_BRANCH}]Ejecución fallida en stage [${env.TAREA}]"                   
        }
    }
*/
}


}

return this;