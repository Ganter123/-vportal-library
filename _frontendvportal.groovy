#!/usr/bin/env groovy
def call() {

pipeline {
    agent any 
    options { buildDiscarder(logRotator(numToKeepStr: '10')); timestamps() }   
    parameters { 
    booleanParam(name: 'deploy', defaultValue: 'false', description: 'if you want to deploy')   
    string(name: 'bucketname', defaultValue: 'vconnect-2020/terraform-states/dev', description: 'give name of bucket you want to use')
    string(name: 'bucketfile', defaultValue: 'data_latest.json', description: 'files of parameter you want to use')
    } 
    environment {
             def username = sh(script: 'git show -s --pretty=%an', returnStdout: true)
            }
    stages {      
	stage ('checkstyle') {        
    	    steps {	
    	        dir('front-end'){
    		_nglint()
    	 }        
    
    }    	        
}
   /*
	stage("SonarQube analysis") {
		steps{
		_sonarscanner()
		}

        }
     */   
    stage ('build') {
        steps{
            dir('front-end'){
            _frontendbuild()
                    
            }
        }
        
    }    
	stage ('publish') {
		steps{
			_publishfrotnend()
		}
	}
	stage ('deploy') {
		when {
                expression {return params.deploy}
	      }
		steps{
		   script {
			
	        _archivefrontend()
		  _frontenddeploy()
			
			}
		}
	}

   }
        post {
        always {
               script{
                        def messages = _generatestagenotificationmessages(_pipelinestagedetail())
                        updateGitlabCommitStatus name: '"${JOB_NAME}"', state: 'failed'
                        _email("${messages['emailTemplate']}")
                      }
              _badgefunction()
             // cleanWs() /* clean up our workspace */
           }
       }
   }

}


