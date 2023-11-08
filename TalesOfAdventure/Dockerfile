FROM amazoncorretto:19

#RUN apt-get update && apt-get upgrade -y && apt-get dist-upgrade -y && apt autoremove -y

WORKDIR /app

COPY ./Compiled/artifacts/tales_of_adventure_jar/tales-of-adventure.jar /app/GameServer.jar

EXPOSE 2777

CMD ["java", "-jar" ,"/app/GameServer.jar"]