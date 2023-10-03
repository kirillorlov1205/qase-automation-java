pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "3.9.4"
      jdk "java"
   }
    triggers {
        cron('0 8 1 * *')
    }
    parameters {
        gitParameter defaultValue: 'chrome', name: 'BROWSER'
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'BRANCH', type: 'PT_BRANCH'
        choice(name: 'SUITE', choices: ['src/test/resources/testng-smoke.xml', 'src/test/resources/testng-regression.xml'], description: 'Test suite')
        string(name: 'DEPLOY_ENV', defaultValue: 'qa', description: 'Environment to test')
        booleanParam(defaultValue: true, description: 'Headless mode', name: 'HEADLESS')
    }

   stages {
      stage('Testing') {
         steps {
            // Get some code from a GitHub repository
            git branch: "${params.BRANCH}", url: 'https://github.com/kirillorlov1205/tms-auto-saucedemo.git'

            // Run Maven on a Unix agent.
            //sh "mvn clean test"

            // To run Maven on a Windows agent, use
            bat "mvn -Dmaven.test.failure.ignore=true -Dbrowser=${params.BROWSER} -Dsurefire.suiteXmlFiles=${params.SUITE} clean test"
         }

         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
            }
         }
      }
      stage('Reporting') {
         steps {
             script {
                     allure([
                             includeProperties: false,
                             jdk: '',
                             properties: [],
                             reportBuildPolicy: 'ALWAYS',
                             results: [[path: 'target/allure-results']]
                     ])
             }
         }
      }
   }
}