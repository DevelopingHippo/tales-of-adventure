FROM amazoncorretto:19

COPY ./GameServer/Compiled/production/tales-of-adventure/ /app

#CMD ["/usr/bin/javac", "-d /app/compiled/","-classpath /app/Resources/lib/*.jar;", "-sourcepath /app/*"]
CMD ["/usr/bin/java", "/app/Launcher.class"]