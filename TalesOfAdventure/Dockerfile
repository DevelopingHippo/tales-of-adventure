FROM amazoncorretto:19

WORKDIR /app

COPY ./GameServer/Compiled/artifacts/tales_of_adventure_jar/tales-of-adventure.jar /app

#CMD ["/usr/bin/javac", "-d /app/compiled/","-classpath /app/Resources/lib/*.jar;", "-sourcepath /app/*"]
CMD ["/usr/bin/java", "/app/tales-of-adventure.jar"]