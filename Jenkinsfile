pipeline {

    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code from GitHub...'

                git branch: 'main',
                    url: 'https://github.com/Manishamkk/spring_second_jenkins_project.git'
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

                sh 'docker build -t spring-second-project:latest .'

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
                    -p 1011:1010 \
                    --name spring-second-project \
                    spring-second-project:latest
                '''

                echo 'Docker container started successfully!'
            }
        }

        stage('Health Check') {
            steps {
                echo 'Checking application...'

                sh '''
                    sleep 10
                    curl -f http://localhost:1010/ || exit 1
                '''

                echo 'Application is running successfully!'
            }
        }
    }

    post {
        success {
            echo 'Spring Boot Second Project deployed successfully!'
        }

        failure {
            echo 'Spring Boot Second Project deployment failed!'
        }
    }
}