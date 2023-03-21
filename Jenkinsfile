pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Clean') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/Thaou15/TD1TestEx2.git'

                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

        }
        
        stage('Test') {
            steps {
                // Get some code from a GitHub repository
                // git 'https://github.com/Thaou15/TD1TestEx2.git'

                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn test"
            }
        }
        
        stage('Install') {
            steps {
                // Get some code from a GitHub repository
                // git 'https://github.com/Thaou15/TD1TestEx2.git'

                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn install"
            }
        }
    }
}
