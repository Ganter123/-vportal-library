#!/usr/bin/env groovy

def call(){
sh  '''npm install
npm install @angular/cli
ng lint --format=checkstyle --force=true | head -n -1 > eslint.xml
'''
}
