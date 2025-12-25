pipeline {
	agent any
	environment {
		DOCKER_HUB_USER = "hajarhaoud"
		APP_NAME = "spring-hello"
	}
	stages {
		stage('Checkout') {
			steps { checkout scm }
		}
		stage('Build & Unit Test') {
			steps {
				sh 'mvn clean test package' // Lance les tests unitaires JUnit
			}
		}
		stage('Docker Build & Push') {
			steps {
				script {
					docker.withRegistry('', 'dockerhub-creds') {
						def img = docker.build("${DOCKER_HUB_USER}/${APP_NAME}")
						img.push('latest')
						img.push("${env.BUILD_ID}")
					}
				}
			}
		}
		stage('Deploy to K8s') {
			steps {
				withKubeConfig([credentialsId: 'k8s-config']) {
					// On déploie dans Kubernetes
					sh 'kubectl apply -f k8s/deployment.yaml'
					sh "kubectl set image deployment/spring-hello spring-hello=${DOCKER_HUB_USER}/${APP_NAME}:${env.BUILD_ID}"
				}
			}
		}
	}
}