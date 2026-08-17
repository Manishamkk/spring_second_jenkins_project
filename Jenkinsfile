pipeline {

    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Manishamkk/spring_second_jenkins_project.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Spring Boot Second Application...'
                sh 'mvn clean package -DskipTests'
            }
        }
        
       stage('Docker Build') {
    steps {
        echo 'Building Docker Image...'

        sh 'docker build -t spring-first-project:latest .'

        echo 'Docker Build stage successful!'
    }
}

stage('Docker Run') {
    steps {
        echo 'Starting Docker Container...'

        sh '''
            docker stop spring-first-project || true
            docker rm spring-first-project || true
            docker run -d -p 1010:1010 --name spring-first-project spring-first-project:latest
        '''

        echo 'Docker Container started successfully!'
    }
}

    }

    post {
        success {
            echo 'Build spring second applicationsuccessful!'
        }

        failure {
            echo 'Build spring second application failed!'
        }
    }
}