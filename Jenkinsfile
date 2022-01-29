pipeline{
    agent any

    environment {
        DOCKERHUB_CREDENTIALS=credentials('docker-hassanbenhzaine')
    }

    stages {
        stage('Build project with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t hassanbenhzaine/gestionemployesv1:latest .'
            }
        }
        stage('Login to Docker Hub') {

            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push to Docker Hub') {

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