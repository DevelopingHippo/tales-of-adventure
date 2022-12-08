# Base Alpine Linux based image with OpenJDK JRE only
FROM amazoncorretto:19
# copy application WAR (with libraries inside)
COPY ./GameServer /app
# specify default command
CMD ["/usr/bin/javac", "-d /app/compiled/","-classpath /app/Resources/lib/*.jar;", "-sourcepath /app/*"]
CMD ["/usr/bin/java", "/app/compiled/"]