FROM openjdk:8
LABEL authors="chenda"
VOLUME /tmp
ADD target/voice-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","-Dfile.encoding=UTF-8", "-Xms1024M","-Xmx1024M", "/app.jar"]
EXPOSE 8720