def gv

pipeline {
    agent any
    
    stages {
        stage('build') {
            steps {
                script {
                    echo 'building app version...'
                }
            }
        }
        stage('test') {
            steps {
                script {
                    echo 'testing the application...'
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo 'deploying docker image...'
                }
            }
        }
    }
}
