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