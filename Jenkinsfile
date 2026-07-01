def gv

pipeline {   
    agent any
    tools {
        maven "maven-3.9.16"
    }
    stages {
        stage ("increment version") {
            steps {
                script {
                    echo "incrementing version"
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("test") {
            steps {
                echo "testing the app"
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo 'building the application...'
                    sh 'mvn clean package'
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                        sh "docker build -t tomkley/demo-app:${IMAGE_NAME} ."
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh "docker push tomkley/demo-app:${IMAGE_NAME}"
                    }
                }
            }
        }

        stage("deploy") {
            steps {
                echo "deploying the app"
            }
        }

        stage("version bump") {
            steps {
                script {
                    echo "bumped up version commit and push..."
                    withCredentials([usernamePassword(credentialsId: '5648b55c-a7b7-4608-a836-28ff4c220e64', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                       sh 'git config --global user.email "jenkins@example.com"'
                       sh 'git config --global user.name "jenkins"'
                        
                       sh 'git status'
                       sh 'git branch'
                       sh 'git config --list'

                       echo '%USER%'

                       echo "user confirmed"
                        
                       sh 'git remote set-url origin https://%USER%:%PASS%@github.com/TomKley345/twn-dbc-08-jenkins.git'
                       sh "git add ."
                       // sh 'git commit -m "Jenkins ci: bumping up the version"
                       // sh "git push origin HEAD:dynamic-version-update"
                    }
                }                
            }
        }               
    }
} 
