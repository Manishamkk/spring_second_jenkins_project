pipeline {

    agent any

    tools {
        maven 'maven'
    }

    environment {
        GIT_TAG = "v1.0.${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {

                echo 'Checking out source code from GitHub...'

                git branch: 'main',
                    credentialsId: 'github-token',
                    url: 'https://github.com/Manishamkk/spring_second_jenkins_project.git'
            }
        }

        stage('Git Tag') {
            steps {

                echo "Creating Git tag: ${GIT_TAG}"

                withCredentials([
                    usernamePassword(
                        credentialsId: 'github-token',
                        usernameVariable: 'GIT_USERNAME',
                        passwordVariable: 'GIT_PASSWORD'
                    )
                ]) {

                    sh '''
                        git config user.name "Manisha Kadam"
                        git config user.email "your-github-email@example.com"

                        git tag -a ${GIT_TAG} -m "Release ${GIT_TAG}"

                        git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/Manishamkk/spring_second_jenkins_project.git ${GIT_TAG}
                    '''
                }

                echo "Git tag ${GIT_TAG} created and pushed successfully!"
            }
        }

        stage('Build') {
            steps {

                echo 'Building Spring Boot application...'

                sh 'mvn clean package -DskipTests'

                echo 'Maven build successful!'
            }
        }

        stage('Docker Build') {
            steps {

                echo 'Building Docker image...'

                sh '''
                    docker build -t spring-second-project:${GIT_TAG} .

                    docker tag spring-second-project:${GIT_TAG} spring-second-project:latest
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
                        spring-second-project:${GIT_TAG}
                '''

                echo 'Docker container started successfully!'
            }
        }
    }

    post {

        success {
            echo 'Spring Boot Second Project deployed successfully!'
            echo "Git Tag: ${GIT_TAG}"
            echo "Docker Image: spring-second-project:${GIT_TAG}"
        }

        failure {
            echo 'Spring Boot Second Project deployment failed!'
        }
    }
}