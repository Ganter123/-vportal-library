#!/usr/bin/env groovy

def call(){

dir('v-portal-boilerplate'){
                sh 'aws s3 cp s3://vconnect-2020/terraform-states/dev/data_latest.json .' 
                  sh  '''rds=`cat data_latest.json | grep Address | cut -d: -f2 | tr -d '"'| tr -d ','| tr -d [:blank:]`
                        database=`cat  data_latest.json | grep DBName | cut -d: -f2 | tr -d '"'| tr -d ','| tr -d [:blank:]`
                        ls -la
                        sed -i "s/localhost/$rds/g" .env  
                        rm -rf node_modules
                        zip -r  backend.zip  .  
	               '''
      
    }
}  