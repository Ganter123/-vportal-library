#!/usr/bin/env groovy
def call() {


 pipeline {
    agent any
     parameters { 
    booleanParam(name: 'deploy', defaultValue: 'false', description: 'if you want to deploy')   
    string(name: 'bucketname', defaultValue: 'vconnect-2020/terraform-states/dev', description: 'give name of bucket you want to use')
    string(name: 'bucketfile', defaultValue: 'data_latest.json', description: 'files of parameter you want to use')
    } 
    environment {
             def username = sh(script: 'git show -s --pretty=%an', returnStdout: true)
            }

   
 /*   environment {
        scannerHome = tool 'v-portal'
            }*/
    
    stages {
      stage('PreBuild') {
            steps {
               
                _prebuild()
            }
          }
         
          stage('build'){
          steps{
              
	          sh 'echo eslint is not yet set as there is linting errors'
	            
	           

	       }	
        } 
        
      stage ('test') {
            steps {
                     sh 'echo test execution will be executed here'
                }
              }
       
      stage ('Publish Reports'){
          steps {
             _publishreport()
          }
        }
      
         stage ('deploy') {
		when {
                expression {return params.deploy}
	      }
		steps{
		   script {
			
	       _nodeartifacts()
		  
		  _deploy()
			
			}
		}
	}

 }
    
      post {
          failure {
              script{
                        def messages = _generatestagenotificationmessages(_pipelinestagedetail())
                        updateGitlabCommitStatus name: '"${JOB_NAME}"', state: 'failed'
                        _email("${messages['emailTemplate']}")
                      } 
          }
      
      always {
              _badgefunction()
           //   cleanWs() /* clean up our workspace */
           }
       } 
 
      
   
  } 
}
  
