FROM amazoncorretto:19

COPY ./GameServer /app

CMD ["/usr/bin/javac", "-d /app/compiled/","-classpath /app/Resources/lib/*.jar;", "-sourcepath /app/*"]
CMD ["/usr/bin/java", "/app/compiled/Launcher.class"]