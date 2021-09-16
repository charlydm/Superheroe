FROM openjdk:11-jdk
COPY "./target/superheroe-0.0.1-SNAPSHOT.jar" "superheroe.jar"
EXPOSE 8080
ENTRYPOINT [ "java","-jar","superheroe.jar" ]