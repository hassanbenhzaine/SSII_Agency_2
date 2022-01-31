pipeline{
    agent any

    stages {
        stage('Packaging project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build image') {
            steps {
                sh 'docker build -t hassanbenhzaine/gestionemployesv1:latest .'
            }
        }
        stage('Push') {
            steps {
                sh 'docker push hassanbenhzaine/gestionemployesv1:latest'
            }
        }
    }

}