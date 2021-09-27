FROM openjdk:11-jre
COPY "./target/superheroe-1.jar" "superheroe.jar"
EXPOSE 8080
ENTRYPOINT [ "java","-jar","superheroe.jar" ]
