def codePulling(gitUrl,branch){
    echo 'Pulling code'
    git url: "${gitUrl}", branch: "${branch}"
    echo 'Done pulling code'
}


def buildImage(image,tag){
    echo 'Build started'
    sh "docker build -t ${image}:${tag} ."
    echo 'Done building'
}


def pushToDockerHub(image,tag){
    withCredentials([
    usernamePassword(
    credentialsId: 'dockerHub',
    usernameVariable: 'DOCKER_USER',
    passwordVariable: 'DOCKER_PASS'
    )
    ]) {
    echo 'Pushing image'

    sh "docker login -u $DOCKER_USER -p $DOCKER_PASS"
    sh "docker tag  ${image}:${tag} $DOCKER_USER/${image}:${tag}"
    sh "docker push $DOCKER_USER/${image}:${tag}"

    echo 'Done Pushing'
    }
}

 //  ONLY FOR TESTING IN PRODUCTION ENV WE USE SEPARATE  EC2 FOR TESTING SND DEPLOYMENT 
def deployApp(dockerHubRepo,image){
    echo 'Started using image from DockerHub'
    sh "docker run -d -p 3000:3000 ${dockerHubRepo}/${image}"
    echo 'Done building'
}