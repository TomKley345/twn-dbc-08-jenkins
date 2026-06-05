pipeline {
    agent any
    tools {
        maven "maven-3.9.16"
    }
    stages {
        stage("build jar") {
            steps {
                script {
                    echo "build"
                    sh "mvn package"
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "build"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                        sh 'docker build -t tomkley/demo-app:jma-2.0 .'
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh 'docker push tomkley/demo-app:jma-2.0'
                    }
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploy"
                }
            }
        }
    }
}