#!/usr/bin/env groovy

def call() {
recordIssues(tools: [checkStyle(pattern: 'front-end/eslint.xml', reportEncoding: 'UTF-8')])

}
