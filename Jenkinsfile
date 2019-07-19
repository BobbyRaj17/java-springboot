node{
    stage('checkout') {
        git url: 'https://github.com/BobbyRaj17/java-springboot.git'
    }
    stage('Maven build'){
        sh 'echo mvn install'
    }
    stage('Build') {
                agent {
                    docker {
                      image 'docker:1.12.6'
                      args '-v /var/run/docker.sock:/var/run/docker.sock'
                    }
                }
                steps {
                  sh '''
                    docker build -t ${DOCKER_REGISTRY_URL}/${DOCKER_PROJECT_NAMESPACE}/${IMAGE_NAME}:${RELEASE_TAG} --build-arg APP_NAME=${IMAGE_NAME}  -f app/Dockerfile app/.
                  '''
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${JENKINS_DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWD']])
                {
                  sh '''
                  echo $DOCKER_PASSWD | docker login --username ${DOCKER_USERNAME} --password-stdin ${DOCKER_REGISTRY_URL}
                  docker push ${DOCKER_REGISTRY_URL}/${DOCKER_PROJECT_NAMESPACE}/${IMAGE_NAME}:${RELEASE_TAG}
                  docker logout
                  '''
                }
              }
            }
    Stage('Kube Deployment'){
        sh 'echo kubectl apply -f ks8/java-deployment.yaml -n daytona'
        }
}
