pipeline {
  agent any
  
  stages {
    stage('Clone Repository') {
      steps {
        //git 'https://github.com/alwalidddd/your-repo.git'
        git 'https://github.com/alwalidddd/testingwalid'
      }
    }
    
    stage('Build Docker Image') {
      steps {
        script {
          docker.withRegistry('https://your-docker-registry', 'docker-credentials-id') {
            def customImage = docker.build('nginx')
            customImage.push()
          }
        }
      }
    }
    
    stage('Deploy to Docker') {
      steps {
        script {
          docker.withRegistry('https://your-docker-registry', 'docker-credentials-id') {
            def container = docker.image('nginx').run('-p 80:80 -d')
            // Additional deployment steps (e.g., environment setup) can be added here
          }
        }
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
