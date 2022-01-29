pipeline{

    agent any

    environment {
        DOCKERHUB_CREDENTIALS=credentials('docker-hassanbenhzaine')
    }

    stages {

        stage('Build') {

            steps {
                sh 'docker build -t gestionemployes:latest .'
                sh 'docker tag gestionemployes:latest hassanbenhzaine/gestionemployes:latest'
            }
        }

        stage('Login') {

            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push') {

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