FROM openjdk:22-jdk
VOLUME /tmp
COPY target/*.jar vbs-api.jar
ENTRYPOINT ["java","-jar","/vbs-api.jar"]

# Utiliser l'image OpenJDK comme image de base
#FROM openjdk:22-jdk

## Installer Maven
#RUN apt-get update && \
#    apt-get install -y maven && \
#    rm -rf /var/lib/apt/lists/*
#
## Copier le code source et les fichiers de configuration
#WORKDIR /app
#COPY . .
#
## Compiler le projet avec Maven
#RUN mvn clean package
#
## Exécuter le fichier JAR
#CMD ["java", "-jar", "target/vbs-api.jar"]