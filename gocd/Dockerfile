FROM gocd/gocd-agent-ubuntu-22.04:v23.5.0

USER root
RUN apt update -y
RUN apt upgrade -y
RUN apt install openjdk-17-jdk nodejs docker-compose docker npm -y
USER go