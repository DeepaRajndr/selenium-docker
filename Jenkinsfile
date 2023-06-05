pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //bat - for windows
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //bat - for windows
                sh "docker build -t='DeepaRajndr/selenium-docker' ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //bat - for windows
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push DeepaRajndr/selenium-docker:latest"
			    }
            }
        }
    }
}