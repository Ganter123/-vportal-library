#!/usr/bin/env groovy

def call(message){
    def body = """Check console output at ${BUILD_URL} to view the results.\nJob Summary\n${message}"""
    def subject = "Build status in Jenkins: #${JOB_NAME} - #$BUILD_NUMBER"
    emailext body: body, subject: subject, to: 'darpan.patel@volansys.com'
}
