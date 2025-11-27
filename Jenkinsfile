pipeline {
    agent any

    tools {
        maven "Maven3"
    }

    environment {
        IMAGE_NAME = "coinchanger-app"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Build Docker image') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Run container (test)') {
            when {
                expression { true }
            }
            steps {
                sh 'docker rm -f coinchanger || true'
                sh "docker run -d -p 8080:8081 --name coinchanger ${IMAGE_NAME}"
            }
        }
    }

    post {
        success {
            echo "Build completado correctamente."
        }
        failure {
            echo "El build ha fallado."
        }
    }
}
