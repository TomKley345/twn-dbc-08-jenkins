def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh 'docker build -t tomkley/demo-app:jma-2.1 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push tomkley/demo-app:jma-2.1'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
