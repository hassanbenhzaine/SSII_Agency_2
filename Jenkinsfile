pipeline{
    agent any

    environment {
        DOCKER_CREDENTIALS=credentials('docker-hassanbenhzaine')
    }

    stages {
        stage('Test & build') {
            steps {
                sh 'mvn clean package'
            }
        }


        stage('Build docker image') {
            steps {
                sh 'docker build -t hassanbenhzaine/gestionemployesv1:latest .'
            }
        }
        stage('Login Docker') {

            steps {
                sh 'echo $DOCKER_CREDENTIALS_PSW | docker login -u $DOCKER_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push to docker') {

            steps {
                sh 'docker push hassanbenhzaine/gestionemployesv1:latest'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }

}