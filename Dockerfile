FROM adoptopenjdk:8u212-b04-jre-hotspot-bionic
COPY target/gapstar-0.0.1-SNAPSHOT.jar /app/app.jar
#CMD ["java", "-jar", "/app/app.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
EXPOSE 8080