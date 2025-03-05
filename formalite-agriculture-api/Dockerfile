# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#FROM alpine:latest

#CMD ["/bin/sh"]




# Use an OpenJDK Runtime as a parent image
FROM eclipse-temurin:17-jdk-jammy as base
# Define environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \JAVA_OPTS=""
ENV JAVA_HOME /usr/local/openjdk-17
ENV PATH $PATH:$JAVA_HOME/bin
# Set the working directory to /app
WORKDIR  /app

COPY target/* /app
# Copy the executable into the container at /app
ADD target/*.jar formalite-agriculture-api.jar
#ADD C:\Users\Admin\Documents\dev_acl\serveur_congo\pnep\pnep_backend\regideso-api.jar regideso-api.jar
# Make port 8080 available to the world outside this container
EXPOSE 4211
# Run app.jar when the container launches
#ENTRYPOINT ["java", "-jar", "/app/regidesoapi-congo-central.jar"]
CMD ["java", "-jar", "/app/formalite-agriculture-api.jar"]

#use font

#Add non-free repositories to get MS fonts
RUN apt-get update && apt-get install -y vim
RUN echo "deb http://us-west-2.ec2.archive.ubuntu.com/ubuntu/ trusty multiverse  deb http://us-west-2.ec2.archive.ubuntu.com/ubuntu/ trusty-updates multiverse  deb http://us-west-2.ec2.archive.ubuntu.com/ubuntu/ trusty-backports main restricted universe multiverse" | tee /etc/apt/sources.list.d/multiverse.list
RUN echo ttf-mscorefonts-installer msttcorefonts/accepted-mscorefonts-eula select true |  debconf-set-selections
RUN ["apt-get", "-y", "install", "ttf-mscorefonts-installer"]
RUN dpkg-reconfigure  ttf-mscorefonts-installer
RUN apt-get install -y apt-transport-https apt-utils
RUN apt-get install  --reinstall -y ttf-mscorefonts-installer
