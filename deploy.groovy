pipeline {
  agent any

  stages {
    stage('Clone Repository') {
      steps {
        git 'https://github.com/alwalidddd/testingwalid'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t {your-image-name} .'
      }
    }

    stage('Deploy to Docker') {
      steps {
        sh 'docker run -p 80:80 -d {your-image-name}'
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
