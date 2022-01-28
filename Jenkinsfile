pipeline{

	agent any

	environment {
		DOCKERHUB_CREDENTIALS=credentials('docker-hassanbenhzaine')
	}

	stages {
         stage('Initialize'){
                def dockerHome = tool 'dockerHassan'
                env.PATH = "${dockerHome}/bin:${env.PATH}"
            }

		stage('Build') {


			steps {
				sh 'docker build -t hassanbenhzaine/gestionemployes:latest .'
			}
		}

		stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				sh 'docker push hassanbenhzaine/gestionemployes:latest'
			}
		}
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}