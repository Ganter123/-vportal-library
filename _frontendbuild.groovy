#!/usr/bin/env groovy

def call() {
sh 'aws s3 cp s3://$bucketname/$bucketfile .'
sh '''elb=`cat $bucketfile | grep dns_name | cut -d: -f2 | tr -d '"'| tr -d ','| tr -d [:blank:]`
                  cd src/environments/
                    sed -i "s/localhost/$elb/g" environment.prod.ts                        
		            cd ../..
                    npm install && ng build --prod
                    zip -r front.zip dist/*
                    '''
}
