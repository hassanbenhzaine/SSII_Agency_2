pipeline{

    agent any

    environment {
        DOCKERHUB_CREDENTIALS=credentials('docker-hassanbenhzaine')
    }

    stages {

        stage('Build') {

            steps {
                sh 'docker build -t gestionemployes .'
                sh 'docker tag gestionemployes hassanbenhzaine/gestionemployes'
            }
        }

        stage('Login') {

            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push') {

            steps {
                sh 'docker push hassanbenhzaine/gestionemployes'
            }
        }
    }

	post {
		always {
			sh 'docker logout'
		}
	}

}