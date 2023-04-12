pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        frontendRepositoryName = "lovyparhar/gradeus-frontend"
        backendRepositoryName = "lovyparhar/gradeus-backend"
        tag = "latest"
        frontendImage = ""
        backendImage = ""
    }
    stages {
        stage('Fetch code from github') {
            steps {
                git branch: 'master',
                url: 'https://github.com/lovyparhar/gradeUs',
                credentialsId: 'gradeus-cred'
            }
        }
        stage('Maven Building') {
            steps {
                script{
                    sh 'mvn -f gradeus-backend clean install'
                }
            }
        }
        stage('Docker image creation for backend') {
            steps {
                script{
                    backendImage = docker.build(backendRepositoryName + ":" + tag, "./gradeus-backend")
                }
            }
        }
        stage('Dockerhub backend image push') {
            steps {
                script{
                    // By default, the registry will be dockerhub
                    docker.withRegistry('', 'dockerhub-credentials'){
                        backendImage.push()
                    }
                }
            }
        }
        stage('Docker image creation for frontend') {
            steps {
                script{
                    frontendImage = docker.build(frontendRepositoryName + ":" + tag, "./gradeus-frontend")
                }
            }
        }
        stage('Dockerhub frontend image push') {
            steps {
                script{
                    // By default, the registry will be dockerhub
                    docker.withRegistry('', 'dockerhub-credentials'){
                        frontendImage.push()
                    }
                }
            }
        }
        // stage('Pulling docker image') {
        //     steps {
        //       ansiblePlaybook disableHostKeyChecking: true, installation: 'Ansible', inventory: 'hosts', playbook: 'deploy-playbook.yml'
        //     }
        // }
    }
}