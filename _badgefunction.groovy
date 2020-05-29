#!/usr/bin/env groovy

def call() {
 addBadge icon: 'folder.gif', id: '', link: 'http://gitlab.volansys.com/sachin.pavar/vportal-library.git', text: 'scm'
 addShortText background: '', borderColor: '', color: 'blue', link: 'http://gitlab.volansys.com/gitrepo/vportal.git', text: "$username"
 //addShortText background: '', borderColor: '', color: 'blue', link: 'https://github.com/Ganter123/library.git', text: 'Ganter123'
}
