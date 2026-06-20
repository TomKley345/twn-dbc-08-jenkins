#!/usr/bin/env groovy

@Library('jenkins-shared-library')
def gv

// Use GIT_BRANCH, if BRANCH_NAME does not exist
def branch = env.BRANCH_NAME ?: env.GIT_BRANCH
echo "Branch: ${branch}"

pipeline {
    agent any
    tools {
        maven "maven-3.9.16"
    }
    stages {
        stage("init") {
            steps {
                echo "initializing branch $BRANCH_NAME"
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
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.buildJar()

                }
            }
        }

        stage("build jar from lib") {
            when {
                expression {
                    BRANCH_NAME == "jenkins-shared-library"
                }
            }
            steps {
                script {
                    buildJar()
                }
            }
        }

        stage("build image") {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.buildImage()
                }
            }
        }

        stage("build image from lib") {
            when {
                expression {
                    BRANCH_NAME == "jenkins-shared-library"
                }
            }
            steps {
                script {
                    buildImage()
                }
            }
        }

        stage("deploy") {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
