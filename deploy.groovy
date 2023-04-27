pipeline {
  agent any

  stages {
    stage('Clone Repository') {
      steps {
        git 'https://github.com/your-username/your-repo.git'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t your-image-name .'
      }
    }

    stage('Deploy to Docker') {
      steps {
        sh 'docker run -p 8080:8080 -d your-image-name'
      }
    }
  }

  post {
    success {
      echo 'Deployment successful!'
    }
    failure {
      echo 'Deployment failed!'
    }
  }
}
