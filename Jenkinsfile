def gv

pipeline {

    agent any

    environment {
        // Set a custom environment variable
        NEW_VERSION = '1.3.0'
        // use credentials()-function to retrieve creds stored in Jenkins
        // through PLUGINS
        // 1. Credentails Plugin
        // 2. Credentials Binding Plugin
        SERVER_CREDENTIALS = credentials("testCredsId")
    }

    parameters {
        // string(name: 'VERSION', defaultValue: '', description: 'Version to deploy on Prod')
        choice(name: 'VERSION', choices: ['1.1.1', '1.2.0', '2.0.0'], description: 'Version to deploy on Prod')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Toggle test execution')
    }

    tools {
        maven "maven-3.9.16"
    }
    
    stages {
        stage('build') {
            steps {
                echo 'building app'
                
                // environment used: version
                echo "version ${NEW_VERSION}"
                
                // tools used: maven
                sh "mvn install"
            }
        }
        stage('test') {
            when {
                expression {
                    // parameters used: boolean
                    // following steps only get executed it this expression is true
                    params.executeTests == true
                }
            }
            steps {
                script {
                    echo 'testing the application...'
                }
            }
        }
        stage('deploy') {
            steps {
                // parameters used: choice
                echo "deploying docker image... version ${params.VERSION}"
                withCredentials([
                    usernamePassword(credentials: 'testCredsId', usernameVariable: USER, passwordVariable: PWD)
                ]) {
                    sh "some script ${USER} ${PWD}"
                }
            }
        }
    }
}
