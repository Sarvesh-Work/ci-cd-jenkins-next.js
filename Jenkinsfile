// declarative pipeline using shared library

@Library('shared') _
pipeline {
    agent any

    environment {
        GITHUB_REPO_LINK = "https://github.com/Sarvesh-Work/ci-cd-jenkins-next.js.git"
        REPO_BRANCH      = "main"
        IMAGE            = "next-app"
        TAG              = "latest"
        DOCKER_HUB_REPO  = "sarvesh9075"
    }

    stages {

        stage('Pulling code from git') {
            steps {
                script {
                    mylib.codePulling(GITHUB_REPO_LINK, REPO_BRANCH)
                }
            }
        }

        stage('Building image') {
            steps {
                script {
                    mylib.buildImage(IMAGE, TAG)
                }
            }
        }

        stage('Pushing image to DockerHub') {
            steps {
                script {
                    mylib.pushToDockerHub(IMAGE, TAG)
                }
            }
        }

        // ONLY FOR TESTING — IN PRODUCTION USE SEPARATE EC2 FOR TEST AND DEPLOYMENT
        stage('Deploying the code') {
            steps {
                script {
                    mylib.deployApp(DOCKER_HUB_REPO, IMAGE)
                }
            }
        }
    }
}
