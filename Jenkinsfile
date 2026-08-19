pipeline {

    agent any

    tools {
        maven 'maven'
    }

    environment {
        GIT_TAG = "v1.0.${BUILD_NUMBER}"
        IMAGE_NAME = "spring-second-project"
    }

    stages {

        stage('Checkout') {
            steps {

                echo 'Checking out code from GitHub...'

                git branch: 'main',
                    credentialsId: 'github-token',
                    url: 'https://github.com/Manishamkk/spring_second_jenkins_project.git'
            }
        }

        stage('Build') {
            steps {

                echo 'Building Spring Boot project...'

                sh 'mvn clean package -DskipTests'

                echo 'Build completed successfully!'
            }
        }

        stage('Git Tag') {
            steps {

                echo "Creating Git Tag: ${GIT_TAG}"

                withCredentials([
                    usernamePassword(
                        credentialsId: 'github-token',
                        usernameVariable: 'GIT_USERNAME',
                        passwordVariable: 'GIT_PASSWORD'
                    )
                ]) {

                    sh '''
                        git config user.name "Manisha Kadam"
                        git config user.email "your-email@gmail.com"

                        git tag -a "${GIT_TAG}" -m "Release ${GIT_TAG}"

                        git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/Manishamkk/spring_second_jenkins_project.git "${GIT_TAG}"
                    '''
                }

                echo "Git Tag ${GIT_TAG} created and pushed successfully!"
            }
        }

        stage('Docker Build') {
            steps {

                echo 'Building Docker image...'

                sh '''
                    docker build -t ${IMAGE_NAME}:${GIT_TAG} .
                    docker tag ${IMAGE_NAME}:${GIT_TAG} ${IMAGE_NAME}:latest
                '''

                echo 'Docker image created successfully!'
            }
        }

        stage('Docker Run') {
            steps {

                echo 'Starting Docker container...'

                sh '''
                    docker stop spring-second-project || true
                    docker rm spring-second-project || true

                    docker run -d \
                        -p 4041:4040 \
                        --name spring-second-project \
                        ${IMAGE_NAME}:${GIT_TAG}
                '''

                echo 'Docker container started successfully!'
            }
        }
    }
  

    post {

        success {
            echo 'Deployment Successful!'
            echo "Git Tag: ${GIT_TAG}"
            echo "Docker Image: ${IMAGE_NAME}:${GIT_TAG}"
        }

        failure {
            echo 'Pipeline Failed!'
        }
    }
}