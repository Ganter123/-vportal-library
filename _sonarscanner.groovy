#!/usr/bin/env groovy

def call(){
 
 withSonarQubeEnv('v-portal') {
                 sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey= -Dsonar.sources=. -Dsonar.host.url=http:// -Dsonar.login="
                   }
 
}     