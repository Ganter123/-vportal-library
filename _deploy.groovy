#!/usr/bin/env groovy

def call() {
    sh 'rm -rf /var/lib/jenkins/jobs/ci-vportal-backend/builds/latest/* '
    sh 'cp  -r /var/lib/jenkins/jobs/ci-vportal-backend/builds/${BUILD_NUMBER}/archive/* /var/lib/jenkins/jobs/ci-vportal-backend/builds/latest/'
        build 'backend-vportal'
}

