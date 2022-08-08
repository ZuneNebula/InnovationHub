#!/bin/bash

set -f

server=$DEPLOY_DEV_SERVER
user=$DEPLOY_USER
branch=$DEPLOY_BRANCH
gittoken=$DEPLOY_GITLAB_TOKEN
gituser=$DEPLOY_GITLAB_USER

echo "Deploying project on server ${server} as ${user} from branch ${branch}"

apt-get update && apt-get install -y openssh-client

command_fresh_clone="ls -ltr && \
 git clone https://${gituser}:${gittoken}@myrepos.stackroute.niit.com/innovationhub/innovationhub.git -b ${branch} && \
 cd innovationhub && \
 echo 'Deploying the Application' && \
 mvn clean install -DskipTests -B && \
 docker-compose up --build -d --remove-orphans && \
 echo 'DONE DEPLOYING'"


command_rolling_update="ls -ltr && \
 cd innovationhub && \
 git fetch --all && \
 git checkout ${branch} && \
 git pull origin ${branch} && \
 echo 'Re Deploying the Application' && \
 mvn clean install -DskipTests -B && \
 docker-compose up --build -d --remove-orphans && \
 echo 'DONE RE-DEPLOYING'"

echo "About to run the command: " $command


command="if [ ! -d /home/${user}/${CI_PROJECT_NAME} ]; then ${command_fresh_clone}; else ${command_rolling_update}; fi"

echo "About to run the command: " $command

ssh $user@$server $command
