#!/usr/bin/env groovy

def call() {
    sh 'rm -rf /var/lib/jenkins/jobs/ci-vportal-frontend/builds/latest/* '
    sh 'cp  -r /var/lib/jenkins/jobs/ci-vportal-frontend/builds/${BUILD_NUMBER}/archive/* /var/lib/jenkins/jobs/ci-vportal-frontend/builds/latest/'
        build 'frontend-vportal'
}
