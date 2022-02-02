pipeline{
    agent any

    environment {
        DOCKERHUB_CREDENTIALS=credentials('docker-hassanbenhzaine')
    }

    stages {
        stage('Build Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker') {
            steps {
                sh 'docker build -t hassanbenhzaine/gestionemployesv1:latest .'
            }
        }
        stage('Login Docker') {

            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push Docker') {

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